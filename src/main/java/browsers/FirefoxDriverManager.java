package browsers;

import common.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", Constants.getConstants().getDownloadFilePath() + "Firefox.log");
        options.addPreference("browser.download.useDownloadDir", true);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf;text/xml;application/xml;application/x-winzip, application/x-pdf, application/x-gzip");
        options.addPreference("pdfjs.disabled", true);
        return new FirefoxDriver(options);
    }
}
