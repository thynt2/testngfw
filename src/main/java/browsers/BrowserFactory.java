package browsers;

import org.openqa.selenium.WebDriver;

public interface BrowserFactory {
    public abstract WebDriver getBrowserDriver();
}
