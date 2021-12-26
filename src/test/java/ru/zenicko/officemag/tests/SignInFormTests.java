package ru.zenicko.officemag.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.zenicko.officemag.config.app.App;
import ru.zenicko.officemag.config.user.UserConfig;
import ru.zenicko.officemag.config.user.UserData;
import ru.zenicko.officemag.helper.TestData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("1.2.3. Sign-in")
public class SignInFormTests extends BaseTest {
    static UserConfig existUser;

    @BeforeAll
    static void configureBaseUrl() {
        Configuration.baseUrl = App.config.webUrl();
    }

    @BeforeEach
    void openPage() {
        Configuration.timeout = 6000;
        step("Open the Sign-in page", () -> {
            open("/auth/");
            $("#authorizationHeader").shouldHave(Condition.text("Вход"));
        });
        existUser = UserData.UserFactory("existuser");
    }

    @Test
    @DisplayName("1.2.3.1. Enter by EXISTING login and CORRECT password")
    @Tag("1.2.3.1")
    @Feature("1.2.3. Sign-in")
    @Story("Enter by EXISTING login and CORRECT password")
    void shouldSignInByValidUserTest() {
        step("Set field username", () -> {
            $("#USER_LOGIN").setValue(existUser.email());
        });
        step("Set field password", () -> {
            $("#USER_PASSWORD").setValue(existUser.password());
        });
        step("Submit the username and the password", () -> {
            $("[name=Login]").click();
        });
        step("Check the full name of the user in a account", () -> {
            $(".User__item span.pseudoLink").click();
            $(".UserInfo__name").
                    shouldHave(Condition.text(existUser.firstName() + " " +existUser.lastName()));
        });
    }

    @Test
    @DisplayName("1.2.3.2. Enter by UN-EXISTING login")
    @Feature("1.2.3. Sign-in")
    @Story("Enter by UN-EXISTING login")
    void shouldNotSignInByUnValidUsernameTest() {
        step("Set field username by a invalid username", () -> {
            $("#USER_LOGIN").setValue("185354" + existUser.email());
        });
        step("Set field password", () -> {
            $("#USER_PASSWORD").setValue(existUser.password());
        });
        step("Submit the username and the password", () -> {
            $("[name=Login]").click();
        });
        step("Check the error message is visible", () -> {
            $(".TipTip__content .AccountError").shouldHave(Condition.text("Данные учётной записи указаны неверно"));
        });
    }

    @Test
    @DisplayName("1.2.3.3. Enter by EXISTED login and UNCORRECTED password")
    @Feature("1.2.3. Sign-in")
    @Story("Enter by EXISTED login and UNCORRECTED password")
    void shouldNotSignInByUnValidPasswordTest() {
        step("Set field username by a valid username", () -> {
            $("#USER_LOGIN").setValue("185354" + existUser.email());
        });
        step("Set field password by invalid password", () -> {
            $("#USER_PASSWORD").setValue("111" + existUser.password());
        });
        step("Submit the username and the password", () -> {
            $("[name=Login]").click();
        });
        step("Check the error message is visible", () -> {
            $(".TipTip__content .AccountError").shouldHave(Condition.text("Данные учётной записи указаны неверно"));
        });
    }

    @Test
    @DisplayName("1.2.3.4. Enter by empty fields login and password")
    @Feature("1.2.3. Sign-in")
    @Story("Enter by empty fields login and password")
    void shouldNotSignInByEmptyFieldsTest() {
        step("Submit the username and the password", () -> {
            $("[name=Login]").click();
        });
        step("Check the error message is visible", () -> {
            $(".TipTip__content .AccountError").shouldHave(Condition.text("Данные учётной записи указаны неверно"));
        });
    }
}
