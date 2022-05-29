package seleniumpractice;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _07HandleWebTable {

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
		driver.get(prop.getProperty("webTable"));
		System.out.println(driver.getCurrentUrl());
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void handleTableData() {

		WebElement ipolist = driver.findElement(By.xpath("//h2[contains(text(),'Largest 10 IPOs in the Last')]"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView();", ipolist);
		List<WebElement> columns = driver.findElements(By.xpath("//table[2]//thead[1]//tr[1]/th"));
		int colnum = columns.size();
		System.out.println("No of columns: " + colnum);

		for (WebElement e : columns) {
		System.out.print(e.getText() + " | ");
		}
		System.out.println();

		System.out.println(
		"Issuer name: " + driver.findElement(By.xpath("//table[2]//tbody[1]//tr[1]//td[3]")).getText());

		List<WebElement> rowitems = driver.findElements(By.xpath("//table[2]//tbody[1]//tr"));
		int rownum = rowitems.size();

		for (int i = 1; i <= rownum; i++) {
			String issuer = driver.findElement(By.xpath("//table[2]//tbody[1]//tr[" + i + "]//td[3]")).getText();
			System.out.println(issuer);
		}
		
		System.out.println("Complete Table Data\n"+"====================================");
		
		}

}
