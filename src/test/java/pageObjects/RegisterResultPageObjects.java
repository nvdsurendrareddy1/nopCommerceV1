package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterResultPageObjects extends BasePage {
	
	WebDriver driver;
	
	public RegisterResultPageObjects(WebDriver driver) {
		
		super(driver);
		
	}
	
	@FindBy(xpath = "//div[text()='Your registration completed']")
	WebElement registerText;
	
	@FindBy(xpath = "//a[@class='button-1 register-continue-button']")
	WebElement linkContinue;
	
	@FindBy(xpath = "//h2[text()='Welcome to our store']")
	WebElement textWelcome;
	
	public String RegisterText() {
		return registerText.getText();
	}
	
	public void ClickContinue() {
		linkContinue.click();
	}
	
	public String WelcomeText() {
		return textWelcome.getText();
	}

}
