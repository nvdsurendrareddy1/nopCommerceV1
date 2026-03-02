package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPageObjects extends BasePage {

	WebDriver driver;

	public RegisterPageObjects(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath = "//input[@id='gender-male']")
	WebElement genderRadioButton;

	@FindBy(xpath = "//input[@id='FirstName']")
	WebElement firstName;

	@FindBy(xpath = "//input[@id='LastName']")
	WebElement lastName;

	@FindBy(xpath = "//input[@id='Email']")
	WebElement email;

	@FindBy(xpath = "//input[@id='Company']")
	WebElement companyName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	WebElement confirmPassword;

	@FindBy(xpath = "//button[@id='register-button']")
	WebElement registerButton;

	public void selectGender() {
		genderRadioButton.click();
	}

	public void enterFirstName(String firstname) {
		firstName.sendKeys(firstname);
	}

	public void enterLastName(String lastname) {
		lastName.sendKeys(lastname);
	}

	public void enterEmail(String eMail) {
		email.sendKeys(eMail);
	}

	public void enterCompanyName(String companyname) {
		companyName.sendKeys(companyname);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void enterConfirmPassword(String confirmpwd) {
		confirmPassword.sendKeys(confirmpwd);
	}

	public void clickRegister() {
		registerButton.click();
	}
	
}
