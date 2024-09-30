package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    WebDriver driver;
    
    
    
    @FindBy(linkText="edit your account information")
    WebElement editaccountinformationoption;
    
    public AccountPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
	}

	public WebElement editaccountinformationoption() {
    	return editaccountinformationoption;
    }
}
