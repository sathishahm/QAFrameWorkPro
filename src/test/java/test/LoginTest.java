package test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountsPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	public WebDriver driver;
	Logger logs ;

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException {
		
		Logger logs = LogManager.getLogger(LoginTest.class.getName());
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropdown().click();
		logs.debug("Clicked on My Account dropdown");
		landingpage.Loginoption().click();
		logs.debug("Clicked on login option");

		LoginPage LoginPage = new LoginPage(driver);
		LoginPage.username().sendKeys(email);
		logs.debug("Email addressed got entered");
		LoginPage.password().sendKeys(password);
		logs.debug("Password got entered");
		LoginPage.Login().click();
		logs.debug("Clicked on Login Button");

		AccountsPage accountPage = new AccountsPage(driver);

		String actualResult = null;

		try {
			if (accountPage.editAccountOption().isDisplayed()) {
				logs.debug("User got logged in");
				actualResult = "Successfull";
			}

		} catch (Exception e) {
			logs.debug("User didn't log in");
			actualResult = "Failure";

		}

		Assert.assertEquals(actualResult, expectedResult);
		logs.info("Login Test got passed");
	}

	@BeforeMethod

	public void beforeApplication() throws IOException {
		
		Logger logs = LogManager.getLogger(LoginTest.class.getName());
		
		driver = intializeDriver();
		logs.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		logs.debug("Navigated to application URL");
	}

	@AfterMethod
	public void closure() {
		Logger logs = LogManager.getLogger(LoginTest.class.getName());
		driver.close();
		logs.debug("driver got closed");
		
	}

	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = { { "arun.selenium@gmail.com", "Second@123", "Successfull" },
				{ "dummy2gmail.com", "test123", "Failure" } };
		return data;

	}

}
