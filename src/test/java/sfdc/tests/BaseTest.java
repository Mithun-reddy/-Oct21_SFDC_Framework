package sfdc.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import sfdc.configs.TestConstants;
import sfdc.pages.CreateAccountPage;
import sfdc.pages.LoginPage;
import sfdc.utilities.BasicUtilities;
import sfdc.utilities.DataUtilities;

public class BaseTest {

	public static WebDriver driver = null;
	public BasicUtilities bu = new BasicUtilities();
	public DataUtilities dataUtil = new DataUtilities();
	public LoginPage loginPage = null;

	public static ExtentTest test = null;
	public static ExtentReports report = null;
	public Logger logger = Logger.getLogger(getClass().getSimpleName());

	public WebDriver getDriver(String browserName, boolean enableHeadlessMode) {
		String sBrowserName = browserName.toLowerCase();
		switch (sBrowserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			logger.info("Chromedriver setup is complete");
			if (enableHeadlessMode) {
				driver = new ChromeDriver(enableHeadlessMode());
				logger.info("Chromedriver headless mode configured");
			} else {
				driver = new ChromeDriver();
			}
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
//			logger.warn("driver is null");
			driver = null;
		}
		return driver;
	}

	public void initializeReport() {
		String appendTimeToReport = new SimpleDateFormat("yyyymmddhhmm").format(new Date());
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(
				TestConstants.sReportPath + "//sfdc_report" + appendTimeToReport + ".html");
		report.attachReporter(htmlReport);
		logger.info("extent html report configured");
	}

	public ChromeOptions enableHeadlessMode() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--ignore-certificate-errors");
		return options;
	}

	public void initializeLogging() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(TestConstants.sLog4JpropertiesPath);
		prop.load(fis);
		PropertyConfigurator.configure(fis);
	}

}
