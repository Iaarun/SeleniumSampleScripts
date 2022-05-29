package seleniumpractice;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _008FileUploadDownload {

	WebDriver driver;
	FileInputStream fis;
	Properties prop;
	String filePath = "D:\\eclipse-workspace\\SeleniumSampleScripts\\src\\test\\java\\doc.properties";

	@BeforeMethod
	public void _setUp() {
		try {
			fis = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fis);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "D:\\eclipse-workspace\\SeleniumSampleScripts\\downloads");

		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("filedownloadurl"));
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	
	@Test
	public void fileDownload() throws InterruptedException {
		
	//	String xpath = "//div[@class='entry-content']//div[2]//div[1]//div[1]//div[1]//div[3]//a[1]";
	//	JavascriptExecutor js= ((JavascriptExecutor)driver);
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
	//	js.executeScript("arguments[0].scrollIntoView();", dwnloadbtn);
		WebElement dwnloadbtn = driver.findElement(By.xpath("//a[@class='wpdm-download-link download-on-click btn btn-primary ']"));
		
		dwnloadbtn.click();
		Thread.sleep(3000);
	}
}
