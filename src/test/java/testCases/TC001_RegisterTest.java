package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageObjects;
import pageObjects.RegisterPageObjects;
import pageObjects.RegisterResultPageObjects;
import testBase.BaseClass;

public class TC001_RegisterTest extends BaseClass {
	
	
	
	@Test(groups="Sanity")
	public void verifyRegistration() throws InterruptedException {
		
		logger.info("**** TC001_RegisterTest case is started ****");
		
		HomePageObjects homepageobjects = new HomePageObjects(getDriver());
		homepageobjects.ClickRegister();
		
		logger.info("Entering Registration Details");
		RegisterPageObjects registerpageobjects = new RegisterPageObjects(getDriver());
		registerpageobjects.selectGender();
		Thread.sleep(Duration.ofSeconds(2));
		registerpageobjects.enterFirstName(randomString().toUpperCase());
		registerpageobjects.enterLastName(randomString().toUpperCase());
		registerpageobjects.enterEmail(randomString().toUpperCase()+"@gmail.com");
		registerpageobjects.enterCompanyName(randomString().toUpperCase());
		
		String password = randomString();
		registerpageobjects.enterPassword(password);
		registerpageobjects.enterConfirmPassword(password);
		logger.info("Entering Registration Details Entered");
		
		registerpageobjects.clickRegister();
		
		logger.info("Registration Results Page Opened");
		RegisterResultPageObjects registerresultpageobjects = new RegisterResultPageObjects(getDriver());
		String Registertext = registerresultpageobjects.RegisterText();
		Assert.assertEquals("Your registration completed", Registertext);
		logger.info("Registration Results Page Closed");
		Thread.sleep(Duration.ofSeconds(2));
		registerresultpageobjects.ClickContinue();
		Thread.sleep(Duration.ofSeconds(2));
		logger.info("Login successful and entered in to Welcome Page");
		String WelcomeTextmsg = registerresultpageobjects.WelcomeText();
		logger.info("**** TC001_RegisterTest case is successfully executed ****");
		Assert.assertEquals("Welcome to our store", WelcomeTextmsg);
		
	} 
	
	
}
