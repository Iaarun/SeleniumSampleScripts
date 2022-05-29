package testngDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _002Depedency {
	WebDriver driver;

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php");
		WebElement signIn = driver.findElement(By.xpath("//a[@class='login']"));
		signIn.click();

		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		email.sendKeys("cbatest7@gmail.com");
		WebElement pass = driver.findElement(By.xpath("//input[@id='passwd']"));
		pass.sendKeys("cba@test");
		WebElement signInbtn = driver.findElement(By.id("SubmitLogin"));
		signIn.click();
	}

	
	public void launchApplication() {
		driver.get("http://automationpractice.com/index.php");
	}

	@Test
	public void signIn() {
		WebElement img=	driver.findElement(By.xpath("//img[@alt='My Store']"));
		System.out.println("Logo Displayed: "+img.isDisplayed());
	}

	@Test
	public void signOut() {
		
		String title = driver.getTitle();
		System.out.println("Title is "+title);
	//	WebElement signout = driver.findElement(By.xpath("//a[@title='Log me out']"));
	//	signout.click();
	}

	@AfterTest
	public void tearDown() {
		WebElement signout = driver.findElement(By.xpath("//a[@title='Log me out']"));
		signout.click();
		driver.quit();
	}
}
