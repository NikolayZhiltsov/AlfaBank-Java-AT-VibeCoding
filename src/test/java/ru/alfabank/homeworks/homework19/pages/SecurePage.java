package ru.alfabank.homeworks.homework19.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class SecurePage {
    private final SelenideElement subheader = $(".example h4");
    private final SelenideElement logoutButton = $("a[href='/logout']");

    public SecurePage checkHeader() {
        subheader.shouldHave(text("Welcome to the Secure Area"));
        return this;
    }

    public SecurePage checkLogout() {
        logoutButton.shouldBe(visible);
        return this;
    }

    public void logout() {
        logoutButton.click();
    }
}
