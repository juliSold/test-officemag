package ru.zenicko.officemag.tests;

import com.codeborne.selenide.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import ru.zenicko.officemag.config.app.App;
import ru.zenicko.officemag.helper.TestData;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Test suit: test the Registration Form")
@Tag("RegistrationFormPageTests")
public class RegistrationFormPageTests extends BaseTest {
    TestData newUser = TestData.dataFactory("ru");

    @BeforeAll
    static void configureBaseUrl() {
        Configuration.baseUrl = App.config.webUrl();
    }

    @BeforeEach
    void openRegistrationFormPage() {
        Configuration.timeout = 10000;

        step("Open the Registration page", () -> {
            open("/auth/register.php");
        });
    }

    private void setRegionAndTown(String region, String town) {

        $(byText("Выберите ваш город")).click();

        $$("li.CitySelectWindow__region a").get(0).should(Condition.exist);
        ElementsCollection fieldsRegion = $$("li.CitySelectWindow__region a");

        boolean bflag = false;
        for (SelenideElement field : fieldsRegion) {
            if (field.getText().contentEquals(region)) {
                if (field.parent().getAttribute("class").indexOf("selected") == -1)
                    field.click();
                bflag = true;
                break;
            }
        }
        Assertions.assertTrue(bflag, String.format("Check test data the name %s of the region unfound", region));

        ElementsCollection cities = $$(".CitySelectWindow__cities.CitySelectWindow__cities--selected li a");

        bflag = false;
        for (SelenideElement city : cities) {
            if (city.getText().contentEquals(town)) {
                city.click();
                bflag = true;
                break;
            }
        }
        Assertions.assertTrue(bflag, String.format("Check test data the name %s of the city unfound", town));

    }

    @Test
    @DisplayName("The page of Registration form:  1.2.4.2. Can choose a region and a city")
    @Tag("1.2.4.2")
    @Feature("1.2.4. Registration")
    @Story("Can choose a region and a city")
    void shouldBeSettingCity() {
        step("Set a region and a city", () -> {
            setRegionAndTown(newUser.getRegion(), newUser.getTown());
        });
        step("Check setting a city", () -> {
            $("[data-set-value='#reg_idp_value']").shouldHave(Condition.text(newUser.getTown()));
        });
    }

    @Test
    @DisplayName("The page of Registration form: 1.2.4.1. The window \"Уважаемый клиент, спасибо за регистрацию!\" is visible\n")
    @Tag("1.2.4.1")
    @Feature("1.2.4. Registration")
    @Story("The window \"Уважаемый клиент, спасибо за регистрацию!\" is visible")
    void shouldBeWindowAfterRegistration() {
        String
                region = newUser.getRegion(),
                town = newUser.getTown(),
                firstName = newUser.getFirstName(),
                lastName = newUser.getLastName(),
                codePhoneNumber = newUser.getCodePhoneNumber(),
                phoneNumber = newUser.getPhoneNumber(),
                email = newUser.getEmail(),
                password = newUser.getPassword(); //password.length >= 6
        boolean
                budget = newUser.getBudget(),
                agreeRules = newUser.getAgreeRules(),
                agreeSpam = newUser.getAgreeSpam();

        step("Set a region and a city", () -> {
            setRegionAndTown(region, town);
        });

        step("Fill fields of the registration form ", () -> {
            $("[name='REGISTER[NAME]']").setValue(firstName);
            $("[name='REGISTER[LAST_NAME]']").setValue(lastName);
            $("[name='REGISTER[PERSONAL_MOBILE_CODE]']").setValue(codePhoneNumber);
            $("[name='REGISTER[PERSONAL_MOBILE]']").setValue(phoneNumber);
            $("[name='REGISTER[EMAIL]']").setValue(email);
            $("[name='REGISTER[PASSWORD]']").setValue(password);
            if (budget)
                $("[name='REGISTER[UF_BUDGET]']").click();
            if (agreeRules)
                $("[name='REGISTER[IS_ALLOWED_PERSONAL_DATA]']").click();
            if (!agreeSpam)
                $("[name='REGISTER[IS_ALLOWED_SUBSCRIPTION]']").click();
        });
        step("Submit registration data", () -> {
            $("button.btn.btnDefault.btnGreen.js-personalDataSubmitting.js-ajaxAnimationAndBlock").click();
        });
        step("The window \"Уважаемый клиент, спасибо за регистрацию!\" is visible", () -> {
            $("#fancybox-outer h2.FancyModal__header").shouldHave(Condition.text("Уважаемый клиент, спасибо за регистрацию!"));
        });
    }

    @Test
    @Disabled
    @DisplayName("1.2.4.3 Ошибка при регистрации пользователя с существующим в системе адресом электронной почты")
    @Tag("1.2.4.3")
    @Feature("1.2.4. Registration")
    @Story("Ошибка при регистрации пользователя с существующим в системе адресом электронной почты")
    void shouldBeNotUsersEqualEmail() {
        //Пользователь с таким адресом электронной почты уже существует.
    }
}
