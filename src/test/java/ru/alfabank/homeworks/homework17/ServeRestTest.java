package ru.alfabank.homeworks.homework17;

import io.cucumber.java.After;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import ru.alfabank.homeworks.homework17.models.Usuario;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServeRestTest {

    private static String userId;
    private static String token;
    private static final String userEmail = "spy_" + System.currentTimeMillis() + "@qa.com";

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @Order(1)
    void shouldGetAllUsers() {
        given()
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("quantidade", greaterThan(0))
                .body("usuarios", not(empty()));

    }

    @Test
    @Order(2)
    void shouldFindUserByEmail() {
        String firstUserEmail =
                given()
                        .when()
                        .get("/usuarios")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("usuarios[0].email");

        given()
                .queryParam("email", firstUserEmail)
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .body("quantidade", equalTo(1))
                .body("usuarios[0].email", equalTo(firstUserEmail));
    }

    @Test
    @Order(3)
    void shouldCreateNewUser() {
        String jsonBody = "{"
                + "\"nome\": \"Тайный Покупатель\","
                + "\"email\": \"" + userEmail + "\","
                + "\"password\": \"secret123\","
                + "\"administrador\": \"true\""
                + "}";

        userId =
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonBody)
                        .when()
                        .post("/usuarios")
                        .then()
                        .statusCode(201)
                        .body("message", equalTo("Cadastro realizado com sucesso"))
                        .body("_id", not(emptyString()))
                        .extract()
                        .path("_id");
    }

    @Test
    @Order(4)
    void shouldUpdateUser() {
        String jsonBody = "{"
                + "\"nome\": \"Обновлённый Покупатель\","
                + "\"email\": \"" + userEmail + "\","
                + "\"password\": \"secret123\","
                + "\"administrador\": \"false\""
                + "}";

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .pathParam("_id", userId)
                .when()
                .put("/usuarios/{_id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));
    }

    @Test
    @Order(5)
    void shouldLogin() {
        String jsonBody = "{"
                + "\"email\": \"" + userEmail + "\","
                + "\"password\": \"secret123\""
                + "}";

        token =
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonBody)
                        .when()
                        .post("/Login")
                        .then()
                        .statusCode(200)
                        .body("message", equalTo("Login realizado com sucesso"))
                        .body("authorization", not(empty()))
                        .extract()
                        .path("authorization");
    }

    @Test
    @Order(6)
    void shouldDeleteUser() {
        given()
                .pathParam("_id", userId)
                .when()
                .delete("/usuarios/{_id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

        given()
                .pathParam("_id", userId)
                .when()
                .get("/usuarios/{_id}")
                .then()
                .statusCode(400)
                .body("message", equalTo("Usuário não encontrado"));
    }

    @Test
    @Order(7)
    void shouldGetAllProducts() {
        given()
                .when()
                .get("/produtos")
                .then()
                .statusCode(200)
                .body("quantidade", greaterThan(0))
                .body("produtos[0].nome", not(emptyString()))
                .body("produtos[0].preco", greaterThan(0))
                .body("produtos[0].descricao", not(emptyString()))
                .body("produtos[0].quantidade", greaterThan(0))
                .body("produtos[0]._id", not(emptyString()));
    }

    @Test
    @Order(8)
    void shouldCreateUserFromDto() {
        Usuario usuario = new Usuario();
        usuario.setNome("Тайный покупатель");
        usuario.setEmail(userEmail);
        usuario.setPassword("secret123");
        usuario.setAdministrador("true");

        userId =
                given()
                        .contentType(ContentType.JSON)
                        .body(usuario)
                        .when()
                        .post("/usuarios")
                        .then()
                        .statusCode(201)
                        .body("message", equalTo("Cadastro realizado com sucesso"))
                        .body("_id", not(emptyString()))
                        .extract()
                        .path("_id");
    }

    //Чистим после теста №8 тестовую среду
    @After
    public void tearDown() {
        given()
                .pathParam("_id", userId)
                .when()
                .delete("/usuarios/{_id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

        given()
                .pathParam("_id", userId)
                .when()
                .get("/usuarios/{_id}")
                .then()
                .statusCode(400)
                .body("message", equalTo("Usuário não encontrado"));
    }
}
