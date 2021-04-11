package tests.scenarios;

import com.codeborne.selenide.Configuration;
import config.DriverConfig;
import org.aeonbits.owner.ConfigFactory;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class TestBase {

    static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.headless = false;
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = System.getProperty("browser", "chrome");
        String remoteWebDriver = System.getProperty("remote.web.driver");
        String screenSize = System.getProperty("browser.screen.size");

        if (screenSize != null) {
            Configuration.browserSize = screenSize;
        } else {
            Configuration.startMaximized = true;
        }

        if (remoteWebDriver != null) {
            String user = driverConfig.remoteWebUser();
            String password = driverConfig.remoteWebPassword();
            Configuration.remote = String.format(remoteWebDriver, user, password);

            System.out.println(user);
            System.out.println(password);
            System.out.println(remoteWebDriver);
            System.out.println(String.format(remoteWebDriver, user, password));
        }
    }

    @AfterEach
    void afterEach() {
        attachScreenshot("last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if (System.getProperty("video.storage") != null)
            attachVideo();
        closeWebDriver();
    }
}