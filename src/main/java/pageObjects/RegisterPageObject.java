package pageObjects;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToTextboxByName(String textboxName, String inputValue) {
        waitElementVisible(driver, RegisterPageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
        sendKeyToElement(driver, RegisterPageUI.DYNAMIC_TEXTBOX_BY_NAME, inputValue, textboxName);
    }
    public boolean isLogoImageDisplayed(){
        waitElementVisible(driver, RegisterPageUI.LOGO_IMAGE);
        return isElementDisplayed(driver, RegisterPageUI.LOGO_IMAGE);
    }
    public String getButtonText(){
        waitElementVisible(driver, RegisterPageUI.REGISTER_BUTTON);
        return getElementText(driver, RegisterPageUI.REGISTER_BUTTON);

    }
}
