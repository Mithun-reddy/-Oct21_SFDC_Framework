package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
	
//	Pagefactory in selenium --> define Webelements and reusable functions of that page
//	Stale element exception
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	public WebElement eUsername;
	
	@FindBy(name = "pw")
	public WebElement ePassword;
	
	@FindBy(name = "Login")
	public WebElement eLoginButton;
	
	@FindBy(id = "error")
	public WebElement eErrorMessage;
	
	
	public boolean isLoginPageDisplayed() {
		
		
		return true;
	}
	
	public void loginToApp() {
		
	}
	
	
	
	
	

		
}
