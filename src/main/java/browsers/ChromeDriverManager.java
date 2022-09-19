package browsers;

import common.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;

public class ChromeDriverManager implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-geolocation");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", Constants.getConstants().getDownloadFilePath());
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);

        System.setProperty("webdriver.chrome.args", "--disable--logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");

        return new ChromeDriver(options);
    }
}
