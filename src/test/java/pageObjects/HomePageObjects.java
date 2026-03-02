package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageObjects extends BasePage {

	WebDriver driver;

	public HomePageObjects(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//a[text()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath = "//a[text()='Log in']")
	WebElement linkLogin;

	public void ClickRegister() {
		linkRegister.click();
	}
	
	public void ClickLogin() {
		linkLogin.click();
	}

}
