package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageObjects extends BasePage {
	
	WebDriver driver;
	
	public LoginPageObjects(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement Email;
	
	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;
	
	@FindBy(xpath = "//button[@class='button-1 login-button']")
	WebElement LoginButton;
	
	@FindBy(xpath= "//h2[text()='Welcome to our store']")
	WebElement WelcomeText;
	
	public void enterEmailLogin(String email) {
		Email.sendKeys(email);
	}
	
	public void enterPasswordLogin(String password) {
		Password.sendKeys(password);
	}
	
	public void clickLogin() {
		LoginButton.click();
	}
	
	public String welcomeText() {
		return WelcomeText.getText();
	}
	

}
