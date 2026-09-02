package ru.alfabank.homeworks.homework17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import ru.alfabank.homeworks.homework17.models.CarrinhoRequest;
import ru.alfabank.homeworks.homework17.models.LoginRequest;
import ru.alfabank.homeworks.homework17.models.Produto;
import ru.alfabank.homeworks.homework17.models.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ServeRestExtendedTest {

    private static final List<String> usersToCleanup = new ArrayList<>();
    private static final List<ProductToken> productsToCleanup = new ArrayList<>();

    private static class ProductToken {
        final String productId;
        final String token;

        ProductToken(String productId, String token) {
            this.productId = productId;
            this.token = token;
        }
    }

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterAll
    static void tearDownAll() {
        for (ProductToken product : productsToCleanup) {
            try {
                given()
                        .pathParam("_id", product.productId)
                        .header("Authorization", product.token)
                        .when()
                        .delete("/produtos/{_id}");
            } catch (Exception ignored) {
            }
        }
        for (String userId : usersToCleanup) {
            try {
                given()
                        .pathParam("_id", userId)
                        .when()
                        .delete("/usuarios/{_id}");
            } catch (Exception ignored) {
            }
        }
    }

    private static String uniqueEmail(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "@qa.com";
    }

    private static String createAdminAndLogin() {
        String email = uniqueEmail("extadm");
        Usuario usuario = new Usuario();
        usuario.setNome("Admin Ext");
        usuario.setEmail(email);
        usuario.setPassword("secret123");
        usuario.setAdministrador("true");

        String id = given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .extract()
                .path("_id");
        usersToCleanup.add(id);

        LoginRequest login = new LoginRequest();
        login.setEmail(email);
        login.setPassword("secret123");

        return given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract()
                .path("authorization");
    }

    private static String getAdminToken() {
        return createAdminAndLogin();
    }

    private static String createProduct(String token) {
        Produto produto = new Produto();
        produto.setNome("Produto Ext " + System.currentTimeMillis());
        produto.setPreco(150);
        produto.setDescricao("Descricao de teste");
        produto.setQuantidade(5);

        String id = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .statusCode(201)
                .extract()
                .path("_id");
        productsToCleanup.add(new ProductToken(id, token));
        return id;
    }

    // ========================= /usuarios — негативные сценарии =========================

    @Test
    void shouldRejectDuplicateEmail() {
        String email = uniqueEmail("dup");
        Usuario usuario = new Usuario();
        usuario.setNome("Duplicado");
        usuario.setEmail(email);
        usuario.setPassword("secret123");
        usuario.setAdministrador("true");

        String id = given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .extract()
                .path("_id");
        usersToCleanup.add(id);

        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(400)
                .body("message", equalTo("Este email já está sendo usado"));
    }

    @Test
    void shouldRejectUserWithMissingFields() {
        given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/usuarios")
                .then()
                .statusCode(400)
                .body("nome", equalTo("nome é obrigatório"))
                .body("email", equalTo("email é obrigatório"))
                .body("password", equalTo("password é obrigatório"))
                .body("administrador", equalTo("administrador é obrigatório"));
    }

    @Test
    void shouldReturnNotFoundForUnknownUser() {
        given()
                .pathParam("_id", "aaaaaaaaaaaaaaaa")
                .when()
                .get("/usuarios/{_id}")
                .then()
                .statusCode(400)
                .body("message", equalTo("Usuário não encontrado"));
    }

    @Test
    void shouldReportNothingDeletedForUnknownUser() {
        given()
                .pathParam("_id", "bbbbbbbbbbbbbbbb")
                .when()
                .delete("/usuarios/{_id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Nenhum registro excluído"));
    }

    @Test
    void shouldReturnZeroUsersForUnknownEmailQuery() {
        given()
                .queryParam("email", "nao_existe_" + System.currentTimeMillis() + "@qa.com")
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .body("quantidade", equalTo(0));
    }

    // ========================= /login — негативные сценарии =========================

    @Test
    void shouldRejectLoginWithWrongPassword() {
        LoginRequest login = new LoginRequest();
        login.setEmail(uniqueEmail("badpwd"));
        login.setPassword("wrongpass");

        given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    void shouldRejectLoginWithUnknownEmail() {
        LoginRequest login = new LoginRequest();
        login.setEmail("nobody_" + System.currentTimeMillis() + "@qa.com");
        login.setPassword("qualquer");

        given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    void shouldRejectLoginWithEmptyBody() {
        given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .body("email", equalTo("email é obrigatório"))
                .body("password", equalTo("password é obrigatório"));
    }

    @Test
    void shouldReturnBearerTokenOnLogin() {
        String email = uniqueEmail("bearer");
        Usuario usuario = new Usuario();
        usuario.setNome("Bearer User");
        usuario.setEmail(email);
        usuario.setPassword("secret123");
        usuario.setAdministrador("false");

        String id = given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .extract()
                .path("_id");
        usersToCleanup.add(id);

        LoginRequest login = new LoginRequest();
        login.setEmail(email);
        login.setPassword("secret123");

        String token = given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("message", equalTo("Login realizado com sucesso"))
                .body("authorization", not(empty()))
                .extract()
                .path("authorization");

        Assertions.assertTrue(token != null && token.startsWith("Bearer "),
                "Token должен начинаться с 'Bearer '");
    }

    // ========================= /produtos — CRUD и негативы =========================

    @Test
    void shouldCreateProductWithAuth() {
        String token = getAdminToken();
        Produto produto = new Produto();
        produto.setNome("Produto Criado " + System.currentTimeMillis());
        produto.setPreco(99);
        produto.setDescricao("Produto de teste");
        produto.setQuantidade(10);

        String id = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", not(emptyString()))
                .extract()
                .path("_id");
        productsToCleanup.add(new ProductToken(id, token));
    }

    @Test
    void shouldRejectProductWithoutAuth() {
        Produto produto = new Produto();
        produto.setNome("Sem Token " + System.currentTimeMillis());
        produto.setPreco(10);
        produto.setDescricao("sem token");
        produto.setQuantidade(1);

        given()
                .contentType(ContentType.JSON)
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .statusCode(401)
                .body("message", containsStringIgnoringCase("Token de acesso"));
    }

    @Test
    void shouldRejectDuplicateProductName() {
        String token = getAdminToken();
        String nome = "Produto Duplicado " + System.currentTimeMillis();

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(50);
        produto.setDescricao("original");
        produto.setQuantidade(3);

        String productId = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .statusCode(201)
                .extract()
                .path("_id");
        productsToCleanup.add(new ProductToken(productId, token));

        Produto duplicado = new Produto();
        duplicado.setNome(nome);
        duplicado.setPreco(60);
        duplicado.setDescricao("duplicado");
        duplicado.setQuantidade(1);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(duplicado)
                .when()
                .post("/produtos")
                .then()
                .statusCode(400)
                .body("message", equalTo("Já existe produto com esse nome"));
    }

    @Test
    void shouldUpdateProduct() {
        String token = getAdminToken();
        String productId = createProduct(token);

        Produto atualizado = new Produto();
        atualizado.setNome("Produto Atualizado " + System.currentTimeMillis());
        atualizado.setPreco(250);
        atualizado.setDescricao("Atualizado");
        atualizado.setQuantidade(8);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .pathParam("_id", productId)
                .body(atualizado)
                .when()
                .put("/produtos/{_id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));
    }

    @Test
    void shouldReturnNotFoundForUnknownProduct() {
        given()
                .pathParam("_id", "cccccccccccccccc")
                .when()
                .get("/produtos/{_id}")
                .then()
                .statusCode(400)
                .body("message", equalTo("Produto não encontrado"));
    }

    // ========================= /carrinhos — корзина =========================

    @Test
    void shouldCreateAndGetCart() {
        String token = getAdminToken();
        String productId = createProduct(token);

        CarrinhoRequest cart = new CarrinhoRequest();
        CarrinhoRequest.ItemProduto item = new CarrinhoRequest.ItemProduto();
        item.setIdProduto(productId);
        item.setQuantidade(2);
        cart.setProdutos(Collections.singletonList(item));

        String cartId = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(cart)
                .when()
                .post("/carrinhos")
                .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", not(emptyString()))
                .extract()
                .path("_id");

        given()
                .pathParam("id", cartId)
                .when()
                .get("/carrinhos/{id}")
                .then()
                .statusCode(200)
                .body("_id", equalTo(cartId))
                .body("produtos[0].idProduto", equalTo(productId))
                .body("produtos[0].quantidade", equalTo(2))
                .body("quantidadeTotal", equalTo(2));
    }

    @Test
    void shouldReportNoCartForFinalizeWithoutCart() {
        String token = getAdminToken();

        given()
                .header("Authorization", token)
                .when()
                .delete("/carrinhos/concluir-compra")
                .then()
                .statusCode(200)
                .body("message", equalTo("Não foi encontrado carrinho para esse usuário"));
    }

    @Test
    void shouldReportNoCartForCancelWithoutCart() {
        String token = getAdminToken();

        given()
                .header("Authorization", token)
                .when()
                .delete("/carrinhos/cancelar-compra")
                .then()
                .statusCode(200)
                .body("message", equalTo("Não foi encontrado carrinho para esse usuário"));
    }

    @Test
    void shouldFinalizePurchaseAndReleaseStock() {
        String token = getAdminToken();
        String productId = createProduct(token);

        CarrinhoRequest cart = new CarrinhoRequest();
        CarrinhoRequest.ItemProduto item = new CarrinhoRequest.ItemProduto();
        item.setIdProduto(productId);
        item.setQuantidade(1);
        cart.setProdutos(Collections.singletonList(item));

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(cart)
                .when()
                .post("/carrinhos")
                .then()
                .statusCode(201);

        given()
                .header("Authorization", token)
                .when()
                .delete("/carrinhos/concluir-compra")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));
    }
}
