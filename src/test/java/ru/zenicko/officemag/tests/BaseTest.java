package ru.zenicko.officemag.tests;

import com.codeborne.selenide.WebDriverRunner;
import ru.zenicko.officemag.config.Project;
import ru.zenicko.officemag.helper.AllureAttachments;
import ru.zenicko.officemag.helper.DriverSettings;
import ru.zenicko.officemag.helper.DriverUtils;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith({AllureJunit5.class})
public class BaseTest {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = DriverUtils.getSessionId();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss.SSS");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        AllureAttachments.addScreenshotAs("Last screenshot " + dtf.format(now));
        AllureAttachments.addPageSource();
//        AllureAttachments.attachNetwork(); // todo
        AllureAttachments.addBrowserConsoleLogs();

        WebDriverRunner.clearBrowserCache();
        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}
