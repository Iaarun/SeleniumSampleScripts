package seleniumpractice;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _001HandleDropDown {
	
	WebDriver driver;
	@BeforeMethod
	public void _setUp() {
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 driver.manage().deleteAllCookies();
		 driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void _01handleDropDown() {
		driver.get("http://automationpractice.com/index.php?controller=contact");
		
		//select Subject heading
		
	WebElement heading=	driver.findElement(By.id("id_contact"));
	Select select = new Select(heading);
	
	//find default or first selected option
	System.out.println("Default Selection: "+select.getFirstSelectedOption());
	
	//print all available option from dropdown
	 List<WebElement> alloption= select.getOptions();
	/* 
	 alloption.forEach(x ->{
		 System.out.println(x.getText());
	 });*/
	
		
	}
	
	
}
