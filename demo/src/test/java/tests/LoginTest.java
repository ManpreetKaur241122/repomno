package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String ExpectedResult) throws IOException, InterruptedException {

		LandingPage landingpage = new LandingPage(driver);
		landingpage.myaccountdropdown().click();
		landingpage.loginOption().click();
		Thread.sleep(3000);

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		loginpage.passwordField().sendKeys(password);
		loginpage.loginButton().click();
		
		AccountPage accountpage = new AccountPage(driver);
		String actualResult = null;

		try {
			if (accountpage.editaccountinformationoption().isDisplayed())
				actualResult = "successfull";

		} catch (Exception e) {
			actualResult = "Failure";

		}

		Assert.assertEquals(actualResult, ExpectedResult);

	}

	@BeforeMethod
	public void openApplication() throws IOException {

		driver = initializeBrowser();// to create local driver-driver /return type is webdriver

		driver.get(prop.getProperty("url"));
	}

	@AfterMethod
	public void closure() {
		driver.close();

	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "manpreet123456789@gmail.com", "C@nada1feb2024", "Sucessfull" },
				{ "dumy@gmail.com", "dumy", "failure" } };
		return data;
	}

}
