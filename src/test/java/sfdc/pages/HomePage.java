package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="menu")
	public WebElement eUSerMenu;
	
	@FindBy(name = "pw")
	public WebElement ePassword;
	
	@FindBy(name = "Login")
	public WebElement eLoginButton;
	
	@FindBy(id = "error")
	public WebElement eErrorMessage;
	
	
	public boolean isHomePageDisplayed() {
		
		
		return true;
	}
	
	public void logOut() {
		
	}
	

}
