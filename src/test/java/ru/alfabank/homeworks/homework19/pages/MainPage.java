package ru.alfabank.homeworks.homework19.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private final SelenideElement header = $(".heading");
    private final SelenideElement formAuthenticationLink = $(byLinkText("Form Authentication"));

    public MainPage openPage() {
        open("https://the-internet.herokuapp.com");
        return this;
    }

    public MainPage checkHeader() {
        header.shouldHave(text("Welcome to the-internet"));
        return this;
    }

    public LoginPage clickFormAuthenticationLink() {
        formAuthenticationLink.click();
        return new LoginPage();
    }
}
