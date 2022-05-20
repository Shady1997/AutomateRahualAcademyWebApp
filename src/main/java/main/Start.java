package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import pom.HomePage;
import utility.ExcelUtility;
import utility.Utility;

public class Start extends ClassProperties {
	// npm install -g newman - newman run <collection name> --data <file name> -n
	// <no of iterations> -d <delay time> -e <environment name>

	@Parameters("browser")
	@BeforeTest
	private void prepareClassProperties(String browser) throws IOException {
		readProperty = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\properties\\generalProperties.properties");
		Properties prop = new Properties();
		prop.load(readProperty);

		if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + prop.getProperty("firefoxdriver"));
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + prop.getProperty("chromedriver"));
			driver = new ChromeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser value!!");
			// Change thread count 1 for sequential , 2 for parallel 3 ..browser..
		}

		// declare page objectes which defined in package POM
		js = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();
		homePage = new HomePage(driver);
	}

	@Test(description = "Start Application using selected driver", priority = 1, groups = "smoke")
	private void startApplication() throws InterruptedException {
		// Mazimize current window
		driver.manage().window().maximize();
		// navigate to website
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		// assert if webpage opened correctly
		softAssert.assertEquals(driver.getPageSource().contains("Practice Page"), true);
		// take screenshot to login page
		Utility.captureScreenshot(driver, "HomePage");
	}

	@Test(description = "Check Radio buttons ", priority = 2, groups = "smoke")
	private void checkRadioButtons() throws InterruptedException {
		// choose first radio button
		homePage.radioButton1.click();
		// choose second radio button
		homePage.radioButton2.click();
		// check third radio button
		homePage.radioButton3.click();
		// assert on radio buttons section on home page
		softAssert.assertEquals(driver.getPageSource().contains("Radio Button Example"), true);
		// take screenshot to login page
		Utility.captureScreenshot(driver, "AfterSelectRadioButtons");
	}

	@Test(description = "Check section suggestion class name ", priority = 3, groups = "smoke")
	private void checkSuggestionClassNameSection() throws InterruptedException {
		// send data to suggection class and select name appear
		homePage.suggestionClass.sendKeys("egypt", Keys.DOWN, Keys.ENTER);
		Thread.sleep(2000);
		// click down key to choose suggested element
		homePage.suggestionClass.sendKeys(Keys.DOWN, Keys.ENTER);
		// assert on suggestion class section
		softAssert.assertEquals(driver.getPageSource().contains("Suggession Class Example"), true);
		// take screenshot to login page
		Utility.captureScreenshot(driver, "SuggestionClassSection");
	}

	@Test(description = "Check section DropDown ", priority = 4, groups = "smoke")
	private void checkDropdownSection() throws InterruptedException {
		// choose element from dropdownlist
		Select selectFromDropDown = new Select(homePage.dropDownList);
		// select first option
		selectFromDropDown.selectByIndex(0);
		// select second option
		selectFromDropDown.selectByIndex(1);
		// select third option
		selectFromDropDown.selectByIndex(2);
		// assert if third option last choice
		softAssert.assertEquals(selectFromDropDown.getFirstSelectedOption().getText().toString(), "Option3");
		// take screenshot to login page
		Utility.captureScreenshot(driver, "dropdownAfterLastSelection");
	}

	@AfterTest
	private void closeApplication() {
		driver.quit();
	}

	public static void getScreenshotOnFailure() {
		// send screenshot parameters of failure
		Utility.captureScreenshot(driver, "fail" + LocalDate.now());
	}

}
