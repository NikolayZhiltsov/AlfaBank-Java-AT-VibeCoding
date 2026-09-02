package ru.alfabank.homeworks.homework19.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement header = $(".example h2");
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement submitButton = $("[type='submit']");
    private final SelenideElement errorMessage = $("#flash");
    private final SelenideElement footerLink = $(byLinkText("Elemental Selenium"));

    public LoginPage checkHeader() {
        header.shouldHave(text("Login Page"));
        return this;
    }

    public LoginPage login(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        submitButton.click();
        return this;
    }

    public void checkErrorMessage() {
        errorMessage.shouldBe(visible).shouldHave(text("Your username is invalid!"));
    }

    public LoginPage checkFooter() {
        footerLink.shouldBe(visible);
        return this;
    }
}
