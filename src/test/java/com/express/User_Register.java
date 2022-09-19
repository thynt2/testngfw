package com.express;

import common.BaseTest;
import common.DataHelper;
import common.PageGenerator;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import reportConfig.ExtentManager;

import static org.testng.Assert.assertTrue;

public class User_Register extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;
    DataHelper dataTest;
    String firstName, lastName, email;

    @Parameters({"browser", "serverName"})
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browserName, @Optional("dev") String serverName) {
        driver = getBrowserDriver(browserName, serverName);
        homePage = PageGenerator.getHomePage(driver);
        dataTest = DataHelper.getData();
        firstName = dataTest.getFirstName();
        lastName = dataTest.getLastName();
        email = dataTest.getEmail();
    }

    @Test
    public void Register_New_User() {
        log.info("Click to Register button");
        homePage.clickToRegisterButton();
        registerPage = PageGenerator.getLRegisterPage(driver);
        log.info("Verify Logo image is displayed");
        verifyTrue(registerPage.isLogoImageDisplayed());
        registerPage.inputToTextboxByName("firstName", firstName);
        registerPage.inputToTextboxByName("lastName", lastName);
        registerPage.inputToTextboxByName("email", email);



    }

    @Parameters("browser")
    @AfterClass
    public void afterClass(String browserName) {
        driver.quit();
    }
}
