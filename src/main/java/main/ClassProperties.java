package main;

import java.io.FileInputStream;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pom.HomePage;

public class ClassProperties {

	public static WebDriver driver;
	FileInputStream readProperty;
	protected SoftAssert softAssert;
	JavascriptExecutor js;
	HomePage homePage;
}
