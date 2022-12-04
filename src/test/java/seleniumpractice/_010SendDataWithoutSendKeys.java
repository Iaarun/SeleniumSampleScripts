package seleniumpractice;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _010SendDataWithoutSendKeys {
	
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
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		System.out.println(driver.manage().window().getSize());
		
		Dimension dm = new Dimension(950, 720);
		driver.manage().window().setSize(dm);
	//	driver.manage().window().maximize();
		driver.get(prop.getProperty("loginPage"));
	}
	

//	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	
	@Test
	
	public void senddataWithoutSendKeys() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('email').value='Arun Singh'");
		js.executeScript("document.getElementById('passwd').value='arunsingh'");
	}

}
