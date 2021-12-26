package ru.zenicko.officemag.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.zenicko.officemag.config.app.App;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Test suit: test the main page of the website https://www.officemag.ru")
@Tag("mainpage")
public class MainPageTests extends BaseTest {

    @BeforeAll
    static void configureBaseUrl() {
        Configuration.baseUrl = App.config.webUrl();
    }

    @BeforeEach
    @Link("https://www.officemag.ru")
    void openMainPage() {
        step("Open the main page https://www.officemag.ru)", () -> {
            open("");
        });
    }

    @Test
    @DisplayName("Open the main page")
    @Tag("1.1")
    @Feature("1. The main page")
    @Story("1.1. The main page is exist")
    @Link("https://www.officemag.ru")
    void shouldOpenMainPageTest() {
        step("Page title should have text \"Интернет-магазин ОФИСМАГ: канцтовары, товары для офиса с доставкой.\"", () -> {
            String expectedTitle = "Интернет-магазин ОФИСМАГ: канцтовары, товары для офиса с доставкой.";
            String actualTitle = title();

            Assertions.assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Tag("1.2.1.1")
    @DisplayName("The logo is visible")
    @Feature("1.2.1. Logo")
    @Story("1.2.1.1. The logo is visible")
    @Link("https://www.officemag.ru")
    void shouldBeLogoVisibleTest() {
        step("Logo is visible", () -> {
            $(By.linkText("ОФИСМАГ. Для офиса, склада, производства")).shouldBe(Condition.visible);
        });
    }

    @Test
    @Tag("1.2.1.2")
    @DisplayName("The logo is clickable")
    @Feature("1.2.1. Logo")
    @Story("1.2.1.2. The logo is clickable")
    @Link("https://www.officemag.ru")
    void shouldBeLogoClickableTest() {
        step("Link is clickable", () -> {
            $(By.linkText("ОФИСМАГ. Для офиса, склада, производства")).click();
        });
    }

    @Test
    @Tag("1.2.2.1")
    @DisplayName("The menu is visible")
    @Feature("1.2.2. Menu")
    @Story("1.2.2.1. The menu is visible")
    @Link("https://www.officemag.ru")
    void shouldHaveMenuTest() {
        String town = $(".Header__box.Header__box--navbar div div").getText();
        step(String.format("Menu has a name of a town %s", town), () -> {
            Assertions.assertThat(town).isNotNull().isNotBlank();
        });
        step("Menu has 5 elements: Контакты, Доставка, Оплата, Магазины, Госзакупка малого объема ", () -> {
            $$(".Header__box.Header__box--navbar a").shouldBe(size(5));
        });
    }

    @Test
    @Tag("1.2.2.2")
    @DisplayName("The Search is visible")
    @Feature("1.2.2. Menu")
    @Story("1.2.2.2. The Search is visible")
    @Link("https://www.officemag.ru")
    void shouldHaveSearchTest() {
        String placeHolder = $("[name='q']").getAttribute("placeholder");
        Assertions.assertThat(placeHolder).isEqualTo("Поиск по названию или коду товара");
    }

    @Test
    @Tag("1.2.2.3")
    @DisplayName("The Sign-in is visible")
    @Feature("1.2.2. Menu")
    @Story("1.2.2.3. The Sign-in is visible")
    @Link("https://www.officemag.ru")
    void shouldHaveLoggingTest() {
        step("Sign-in is visible", () -> {
            $(".User a", 0).shouldBe(Condition.visible);
        });
        step("Sign-in has href=/auth/", () -> {
            $(".User a", 0).shouldBe(Condition.href("/auth/"));
        });
        step("Sign-in has text=Войти", () -> {
            $(".User a", 0).shouldHave(Condition.text("Войти"));
        });
    }

    @Test
    @Tag("1.2.2.4")
    @DisplayName("The page Sign-in is exist")
    @Feature("1.2.2. Menu")
    @Story("1.2.2.4. The page Sign-in is exist")
    @Link("https://www.officemag.ru")
    void shouldBeLoggingPageTest() {
        step("The link Sign-in is work", () -> {
            $(".User a", 0).click();
        });
        step("The page Sign-in is exist", () -> {
            Assertions.assertThat(title()).isEqualTo("Вход");
        });
    }

    @Test
    @Tag("1.2.2.5")
    @DisplayName("The Registration is visible")
    @Feature("1.2.2. Menu")
    @Story("1.2.2.5. The Registration is visible")
    @Link("https://www.officemag.ru")
    void shouldHaveRegistrationTest() {
        step("Registration is visible", () -> {
            $(".User a", 1).shouldBe(Condition.visible);
        });
        step("Registration has href=/auth/register.php", () -> {
            $(".User a", 1).shouldBe(Condition.href("/auth/register.php"));
        });
        step("Registration has text=Зарегистрироваться", () -> {
            $(".User a", 1).shouldHave(Condition.text("Зарегистрироваться"));
        });
    }

    @Test
    @Tag("1.2.2.6")
    @DisplayName("The page of Registration form is exist")
    @Feature("1.2.2. Menu")
    @Story("1.2.2.6. The page of Registration form is exist")
    @Link("https://www.officemag.ru")
    void shouldBePageRegistrationFormTest() {
        step("The link to registration form is work", () -> {
            $(".User a", 1).click();
        });
        step("The page registration form is exist", () -> {
            Assertions.assertThat(title()).isEqualTo("Регистрация нового пользователя");
        });
    }

}
