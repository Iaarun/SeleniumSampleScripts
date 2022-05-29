package seleniumpractice;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _05HandleMultiplewindows {
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
		driver.get(prop.getProperty("multipleWindows"));
		System.out.println(driver.getCurrentUrl());
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	
	@Test
	public void _handleMultiplewindows() {
		System.out.println(driver.getTitle());
		//click on tools options
		driver.findElement(By.xpath("//div[normalize-space()='Services']")).click();
		Set<String> allwindowsId= driver.getWindowHandles();
		Iterator<String> it= allwindowsId.iterator();

        String parentId =  it.next();
        String childId =  it.next();
        driver.switchTo().window(childId);
        System.out.println(driver.getTitle());
        driver.close();
        driver.switchTo().window(parentId);
        System.out.println(driver.getTitle());
        
	}

}
