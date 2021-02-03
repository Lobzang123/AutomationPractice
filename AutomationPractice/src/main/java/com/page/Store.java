package com.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Store {

	WebDriver driver ;
	public Store(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#block_top_menu > ul > li:nth-child(3) > a")
	WebElement tshirts;

	@FindBy(css = "#center_column > ul > li > div > div.right-block > h5 > a")
	WebElement fadedShortSleeves;
	
	@FindBy(css = "#add_to_cart > button > span")
	WebElement addToCart;
	
	@FindBy(css ="#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a > span")
	WebElement proceedToCheckout;
	
	@FindBy(tagName = "body")
	WebElement bodyText;
	
	@Test(priority = 0)
	public void chooseTshirts() throws InterruptedException {
		tshirts.click();
		fadedShortSleeves.click();
		addToCart.click();
		boolean addedToCardMsg = driver.getPageSource().contains("Product successfully added to your shopping cart");
		Assert.assertTrue(addedToCardMsg);
		Thread.sleep(7000);
		proceedToCheckout.click();
		Thread.sleep(3000);
		
		boolean ColorSizeMsg = driver.getPageSource().contains("Color : Orange, Size : S");
		Assert.assertTrue(ColorSizeMsg);
		
		boolean unitPrice = driver.getPageSource().contains("$16.51");
		Assert.assertTrue(unitPrice);

	}
}
