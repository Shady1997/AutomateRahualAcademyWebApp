package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@value='radio1']")
	public WebElement radioButton1;

	@FindBy(xpath = "//input[@value='radio2']")
	public WebElement radioButton2;

	@FindBy(xpath = "//input[@value='radio3']")
	public WebElement radioButton3;

	@FindBy(xpath = "//input[@id='autocomplete']")
	public WebElement suggestionClass;

	@FindBy(xpath = "//select[@id='dropdown-class-example']")
	public WebElement dropDownList;

	@FindBy(xpath = "//input[@id='signInSubmit']")
	public WebElement loginButton;

}
