package ru.alfabank.homeworks.homework19.tests;

import org.junit.jupiter.api.Test;
import ru.alfabank.homeworks.homework19.pages.LoginPage;
import ru.alfabank.homeworks.homework19.pages.MainPage;
import ru.alfabank.homeworks.homework19.pages.SecurePage;

public class HerokuLoginTests {
    MainPage mainPage =  new MainPage();
    LoginPage loginPage =  new LoginPage();
    SecurePage securePage =  new SecurePage();

    @Test
    public void shouldLogin() {
        mainPage.openPage()
                .checkHeader()
                .clickFormAuthenticationLink();

        loginPage.checkHeader()
                .login("tomsmith","SuperSecretPassword!");

        securePage.checkHeader()
                .checkLogout()
                .logout();

        loginPage.checkHeader();
    }

    @Test
    public void shouldNotLogin() {
        mainPage.openPage()
                .checkHeader()
                .clickFormAuthenticationLink();

        loginPage.checkFooter()
                .login("admin","1234")
                .checkErrorMessage();
    }
}
