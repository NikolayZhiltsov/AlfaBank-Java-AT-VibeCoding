package ru.alfabank.homeworks.homework19;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HerokuTests {

    @Test
    public void shouldLogin() {
        open("https://the-internet.herokuapp.com");
        $(".heading").shouldHave(text("Welcome to the-internet"));
        $(byLinkText("Form Authentication")).click();
        $(".example h2").shouldHave(text("Login Page"));
        $("#username").click();
        $("#username").setValue("tomsmith");
        $("#password").click();
        $("#password").setValue("SuperSecretPassword!");
        $("form#login button").click();
        $("#flash").shouldHave(text("You logged into a secure area!"));
        $(byText("Logout")).shouldBe(Condition.visible);
        $(byText("Logout")).click();
        $(".example h2").shouldHave(text("Login Page"));
    }

    @Test
    public void shouldNotLogin() {
        open("https://the-internet.herokuapp.com");
        $(".heading").shouldHave(text("Welcome to the-internet"));
        $(byLinkText("Form Authentication")).click();
        $(".example h2").shouldHave(text("Login Page"));
        $(byLinkText("Elemental Selenium")).shouldBe(Condition.visible);
        $("#username").click();
        $("#username").setValue("admin");
        $("#password").click();
        $("#password").setValue("1234!");
        $("form#login button").click();
        $("#flash").shouldHave(text("Your username is invalid!"));
    }
}
