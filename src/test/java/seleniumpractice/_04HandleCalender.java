package seleniumpractice;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _04HandleCalender {
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
		driver.get(prop.getProperty("calenderPage"));
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void _handleCalender1() throws InterruptedException {
		WebElement ele_frame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(ele_frame);

		// click on date picker
		driver.findElement(By.id("datepicker")).click();	

		// get calender title
		String cal_title = driver.findElement(By.className("ui-datepicker-title")).getText();
		System.out.println(cal_title);
		String month = cal_title.split(" ")[0].trim();
		String year = cal_title.split(" ")[1].trim();
		System.out.println("Month "+month+"  \n year "+year);
		String nxtYear = String.valueOf(Integer.parseInt(year) + 1);
		System.out.println(nxtYear);

		while (!(month.equals("October") && year.equals(nxtYear))) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			cal_title = driver.findElement(By.className("ui-datepicker-title")).getText();
			System.out.println(cal_title);
			month = cal_title.split(" ")[0].trim();
			year = cal_title.split(" ")[1].trim();
		}

		driver.findElement(By.xpath("//a[text()='20']")).click();

	}

	public String[] getMonthYearValue(String calenderTitle) {
		return calenderTitle.split(" ");
	}

	public void selectDate(String day, String month, String year) {
		if (month.equals("February") && Integer.parseInt(day) > 29) {
			System.out.println("Wrong date: " + month + day);
			return;
		}

		if (Integer.parseInt(day) > 31) {
			System.out.println("Wrong date: " + month + day);
			return;
		}

		// get calender title
		String cal_title = driver.findElement(By.className("ui-datepicker-title")).getText();

		while (!(getMonthYearValue(cal_title)[0].equals(month) && getMonthYearValue(cal_title)[1].equals(year))) {
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			cal_title = driver.findElement(By.className("ui-datepicker-title")).getText();
		}
		try {
			driver.findElement(By.xpath("//a[text()='" + day + "']")).click();
		} catch (Exception e) {
			System.out.println("Wrong date: " + month + day);
		}
	}

	@Test
	public void testCalender() {
		WebElement ele_frame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(ele_frame);

		// click on date picker
		driver.findElement(By.id("datepicker")).click();
		selectDate("30", "February", "2022");
	}

}
