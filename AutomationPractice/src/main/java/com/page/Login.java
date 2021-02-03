package com.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	WebDriver driver ;
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a.login")
	WebElement signIn;

	@FindBy(css = "#email")
	WebElement username;

	@FindBy(id = "passwd")
	WebElement password;

	@FindBy(id = "SubmitLogin")
	WebElement submitLogin;


	public void signIn() {
		signIn.click();
	}

	public void login(String uname, String passwd) {
		username.sendKeys(uname);
		password.sendKeys(passwd);
		submitLogin.click();
	}
	
}
