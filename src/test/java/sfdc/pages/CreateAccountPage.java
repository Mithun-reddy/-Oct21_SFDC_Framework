package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {

	@FindBy(xpath = "//input[(@name = 'new')]")
	public WebElement newButton;

	public CreateAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String clickOnAccounts() {

		return "AccountName";
	}

	public void createNewView() {

	}
	
	public void createAccount() {
		
		System.out.println("Account created");
	}
	
	public void deleteAccount(String accountName)
	{
		System.out.println("Account deleted"+ accountName);
	}
}
