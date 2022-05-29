package testngDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _001GroupDemo {
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}
	
	@Test(groups = {"titleTest"})
	public void checkTitle() {
		String title = driver.getTitle();
		System.out.println("Title is "+title);
	}
	
	@Test(groups = {"logoTest"})
	public void checklogo() {
	WebElement img=	driver.findElement(By.xpath("//img[@alt='My Store']"));
	System.out.println("Logo Displayed: "+img.isDisplayed());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

