package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void verifyLogin() {

		logger.info("**** TC002_LoginTest case is started ****");

		HomePageObjects homepageobjects = new HomePageObjects(getDriver());
		homepageobjects.ClickLogin();
		logger.info("Clicked on login button");
		LoginPageObjects loginpageobjects = new LoginPageObjects(getDriver());
		loginpageobjects.enterEmailLogin(p.getProperty("email"));
		loginpageobjects.enterPasswordLogin(p.getProperty("password"));
		loginpageobjects.clickLogin();
		logger.info("Successfully Logged into site");

		String textWelcome = loginpageobjects.welcomeText();
		logger.info("**** TC002_LoginTest case is successfully executed ****");
		Assert.assertEquals("Welcome to our store", textWelcome);

	}

}
