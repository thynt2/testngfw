package common;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class PageGenerator {

    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }
    public static RegisterPageObject getLRegisterPage(WebDriver driver) {
        return new RegisterPageObject(driver);
    }
}
