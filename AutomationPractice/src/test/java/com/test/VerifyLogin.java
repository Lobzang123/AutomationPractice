package com.test;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.page.Login;
import com.page.Store;


public class VerifyLogin {

	WebDriver driver = null;
	SoftAssert asserts = new SoftAssert();
	Login login = null;
	Store store = null;

	@BeforeTest
	public void setUp() {
		String user_dir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", user_dir+"\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(6, TimeUnit.SECONDS);
		login = new Login(driver);
	}

	@Test(priority = 0)
	public void signTest() throws InterruptedException {


		login.signIn();
		asserts.assertEquals(driver.getTitle(), "Login - My Store", "Sign is not Successful, Mis-match Signed In title.....");
		Thread.sleep(5000);
	}

	@Test(priority = 1)
	public void loginTest() {

		login.login("jetblue@grr.la", "jetblue");
		asserts.assertEquals(driver.getTitle(), "My account - My Store", "Log In not Successful... Login title doesnt matches.");
	}

	@Test(priority = 2)
	public void storePage() throws InterruptedException {
		store = new Store(driver);
		store.chooseTshirts();	
		Thread.sleep(3000);
		
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
