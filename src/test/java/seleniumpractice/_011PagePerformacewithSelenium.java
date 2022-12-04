package seleniumpractice;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _011PagePerformacewithSelenium {
	
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
	public  void checkPagePerformance() {
		String hyperlink = "http://lambdatest.com";
		driver.get(hyperlink);
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
	Long navigationStart=	(Long)js.executeScript("return window.performance.timing.navigationStart");
	Long responseStart=(Long)	js.executeScript("return window.performance.timing.responseStart");
	Object domComplete=	js.executeScript("return window.performance.timing.domComplete");
	
	System.out.println(responseStart - navigationStart);
				
	}

}
