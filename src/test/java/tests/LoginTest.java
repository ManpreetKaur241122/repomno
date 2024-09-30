package tests;



import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class LoginTest extends Base{
	public WebDriver driver;
	Logger log;
	@Test(dataProvider="getLoginData")
	public void login(String email,String password,String ExpectedResult) throws IOException, InterruptedException  {
	
		LandingPage landingpage=new LandingPage(driver);
		landingpage.myaccountdropdown().click();
		log.debug("clicked on my account dropdown");
		landingpage.loginOption().click();
		log.debug("clicked on login option");
		
		Thread.sleep(3000);
		
		LoginPage loginpage=new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		log.debug("email address got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("password got entered");
		loginpage.loginButton().click();
		log.debug("clicked on login button");
		
		AccountPage accountpage= new AccountPage(driver);
		
	    String actualResult=null;
	    
	    try {
	    if(accountpage.editaccountinformationoption().isDisplayed())
	    	actualResult="successfull";
	        log.debug("user got logged in");
	    }catch(Exception e){
	    	actualResult="Failure";
	    	log.debug("user did not log in");
	    }
	    
	    Assert.assertEquals(actualResult,ExpectedResult);
		
		
	}
	@BeforeMethod
	public void openApplication() throws IOException {
		
	    log = LogManager.getLogger(LoginTest.class);
		driver = initializeBrowser();//to create local driver-driver /return type is webdriver
		log.info("web page initialize");
		driver.get(prop.getProperty("url"));
	}
	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("browser got closed");
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data= {{"arun.selenium@gmail.com","Second@123","Sucessfull"},{"dumy@gmail.com","dumy","failure"}}; 
	return data;
	}
	

}
