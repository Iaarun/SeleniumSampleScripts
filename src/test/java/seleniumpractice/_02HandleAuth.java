package seleniumpractice;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _02HandleAuth {

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
		driver.manage().window().maximize();
		driver.get(prop.getProperty("withoutauthurl"));
	}

	@Test
	public void _handleAuthenticationPopUp() {
		String text = driver.findElement(By.xpath("//body")).getText();
		System.out.println("Without Auth: "+text);
		
		driver.get(prop.getProperty("withauthurl"));
		String text1 = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]")).getText();
		System.out.println("With Auth: "+text1 );
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
