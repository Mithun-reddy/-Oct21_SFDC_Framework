package sfdc.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.utils.FileUtil;

import sfdc.configs.TestWaits;
import sfdc.tests.BaseTest;

public class BasicUtilities {

	public void clickOnElement(WebElement element) {
		element.click();
	}

	public void sendText(String text, WebElement element) {
		element.sendKeys(text);
		BaseTest.test.log(Status.INFO, "Entered text as: "+text);
	}

	public String readTextFromElement(WebElement element) {
		BaseTest.test.log(Status.INFO, "Fetching the text as: "+ element.getText());
		return element.getText();
	}

	public void clearText(WebElement element) {

		element.clear();
	}

	/*
	 * This function returns a true value if element is visible else it will return
	 * false
	 */
	public boolean isElementVisible(WebElement element) {
		try {
			if(element.isDisplayed()) {
				BaseTest.test.log(Status.INFO, "Element is visible");
			}
			return true;
		} catch (Exception e) {
			System.out.println("Exception encountered " + e.getMessage());
			BaseTest.test.log(Status.INFO, "Element is not visible");
//			BaseTest.logger.error("Element is not visible");
			return false;
		}
	}

	/*
	 * This function returns a true value if element is selected i.e if a radio
	 * button or checkbox is selected else it will return false
	 */
	public boolean isElementSelected(WebElement element) {
		try {
			element.isSelected();
			BaseTest.test.log(Status.INFO, "Element is Selected");
			return true;
		} catch (Exception e) {
			System.out.println("Exception encountered " + e.getMessage());
			BaseTest.test.log(Status.INFO, "Element is not visible");
			return false;
		}
	}

	public void selectElementFromDropdown(WebElement element, String dropdownValue) {
		Select s = new Select(element);
		BaseTest.test.log(Status.INFO, "Dropdown is visible");
		s.selectByValue(dropdownValue);
	}

	public void moveFocusOnToElement(WebElement element, WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public void waitForElement(WebElement element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, TestWaits.WAIT_FOR_ELEMENT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public String getScreenCapture(WebDriver driver) throws IOException {
//		Date date = new Date();
		DateFormat df = new SimpleDateFormat();
		Date date = Calendar.getInstance().getTime();
		String dateFormat = df.format(date).replaceAll("/", "_").replaceAll(":", "_").replaceAll(" ", "_");
		String path= System.getProperty("user.dir")+"//target//Reports//"+dateFormat+".PNG";
		System.out.println(path);
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(path);
		FileUtils.copyFile(source, destination);
		return path;
	}

}
