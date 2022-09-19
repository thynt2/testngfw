package pageObjects;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterButton(){
        waitElementClickable(driver, HomePageUI.REGISTER_BUTTON);
        clickToElement(driver, HomePageUI.REGISTER_BUTTON);
    }
}
