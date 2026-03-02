package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPageObjects extends BasePage {
	
	WebDriver driver;
	
	public MyAccountPageObjects(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath= "//h2[text()='Welcome to our store']")
	WebElement WelcomeText;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	WebElement logoutButton;
	
/*	public String welcomeText() {
		return WelcomeText.getText();
	}*/
	
	public boolean isMyAccountPageObjectsExists() 
	{
		try
		{
		return (WelcomeText.isDisplayed());
		} catch (Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout() {
		logoutButton.click();
	}

}
