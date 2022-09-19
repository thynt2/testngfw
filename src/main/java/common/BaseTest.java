package common;

import browsers.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    protected final Log log;

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName, String serverName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;

            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            case SAFARI:
                driver = new SafariDriverManager().getBrowserDriver();
                break;
            default:
                throw new BrowserNotSupport(browserName);
        }
        driver.manage().timeouts().implicitlyWait(Constants.getConstants().getShortTimeout(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(getEnvironmentValue(serverName));
        return driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    protected String getEnvironmentValue(String serverName) {
        String envUrl = null;
        EnvironmentList environment = EnvironmentList.valueOf(serverName.toUpperCase());
        if (environment == EnvironmentList.DEV) {
            envUrl = "https://rbh-ex-dev-web.gcp.alp-robinhood.com/";
        }
        return envUrl;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info(" -------------------------- PASSED -------------------------- ");

        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

}

