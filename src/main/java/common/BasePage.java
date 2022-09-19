package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id") || locatorType.startsWith("ID") || locatorType.startsWith("Id")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class") || locatorType.startsWith("CLASS") || locatorType.startsWith("Class")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name") || locatorType.startsWith("NAME") || locatorType.startsWith("Name")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css") || locatorType.startsWith("CSS") || locatorType.startsWith("Css")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath") || locatorType.startsWith("XPATH") || locatorType.startsWith("Xpath")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type is not support!");
        }
        return by;
    }

    public String getDynamicXpath(String locatorType, String... dynamicValue) {
        if (locatorType.startsWith("xpath") || locatorType.startsWith("XPATH") || locatorType.startsWith("Xpath")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValue);
        }
        return locatorType;
    }

    public WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    public List<WebElement> getWebElements(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValue) {
        getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String value) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(value);
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String value, String... dynamicValue) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        element.clear();
        element.sendKeys(value);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(textItem);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValue) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
        select.selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
        getWebElement(driver, parentXpath).click();
        sleepInsecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        List<WebElement> childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for (WebElement item : childItems) {
            if (item.getText().trim().equals(expectedItemText)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInsecond(1);
                item.click();
                break;
            }
        }
    }

    public void sleepInsecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getElementText(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getText().trim();
    }

    public String getElementText(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).getText().trim();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValue) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locatorType, String... dynamicValue) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isSelected();
    }

    public void hoverToElement(WebDriver driver, String locatorType) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locatorType)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getElementText(driver, locatorType), key).perform();
    }

    public void scrollToBottom(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight");
    }

    public void waitElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.getConstants().getLongTimeOut());
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.getConstants().getLongTimeOut());
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

    public void waitElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.getConstants().getLongTimeOut());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitElementInvisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.getConstants().getLongTimeOut());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

    public void waitElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.getConstants().getLongTimeOut());
        wait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitElementClickable(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.getConstants().getLongTimeOut());
        wait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

    public int getElementSize(WebDriver driver, String locatorType) {
        return getWebElements(driver, locatorType).size();
    }

    public int getElementSize(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElements(driver, getDynamicXpath(locatorType, dynamicValue)).size();
    }

    public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public boolean isElementUndisplay(WebDriver driver, String locatorType) {
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getWebElements(driver, locatorType);
        overrideImplicitTimeout(driver, longTimeout);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }

    }

    public void scrollToElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    private long longTimeout = Constants.getConstants().getLongTimeOut();
    private long shortTimeout = Constants.getConstants().getShortTimeout();

}
