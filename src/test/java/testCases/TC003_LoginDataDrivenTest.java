package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.MyAccountPageObjects;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void verify_LoginDataDrivenTest(String email, String password, String exp) {

		logger.info("**** Starting TC003_LoginDataDrivenTest ****");

		try {
			HomePageObjects homepageobjects = new HomePageObjects(getDriver());
			homepageobjects.ClickLogin();

			LoginPageObjects loginpageobjects = new LoginPageObjects(getDriver());
			loginpageobjects.enterEmailLogin(email);
			loginpageobjects.enterPasswordLogin(password);
			loginpageobjects.clickLogin();

			MyAccountPageObjects myaccountpageobjects = new MyAccountPageObjects(getDriver());
			boolean targetPage = myaccountpageobjects.isMyAccountPageObjectsExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					myaccountpageobjects.clickLogout();
					logger.info("Valid login credentials: Test Case Passed");
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == false) {
					logger.info("Invalid login credentials: Test Case Failed");
					myaccountpageobjects.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("**** Finished TC003_LoginDataDrivenTest ****");
	}
}