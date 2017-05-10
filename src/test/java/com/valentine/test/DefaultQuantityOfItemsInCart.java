package com.valentine.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DefaultQuantityOfItemsInCart {
	private WebDriver driver;
	
	@Test
	public void testNavigationToCart() {
		System.setProperty("webdriver.chrome.driver", "C:\\lib\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://awful-valentine.com/");
		
		driver.findElement(By.cssSelector("[href='#et-offer-post-18']")).click();
		WebElement addToCartPopup = driver.findElement(By.id("fancybox-wrap"));
		Assert.assertTrue(addToCartPopup.isDisplayed());
		
		waitFor(1000);
		WebElement title = driver.findElement(By.cssSelector("#et-offer-post-18 .et_popup_title"));
		Assert.assertEquals(title.getText(), "Never Forget The Special Day!", "Incorrect product title");
		
		waitFor(3000);
		driver.findElement(By.id("addToCart_1")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/store/cart/");

	}

	@Test(dependsOnMethods = "testNavigationToCart")
	public void testDefaultQuantityInCart() { 
		waitFor(2000);
		WebElement quantityFieldValue = driver.findElement(By.cssSelector(".itemQuantity"));
		Assert.assertEquals(quantityFieldValue.getText(),"1", "Incorrect default quantity of items in cart.");//How to get value from the field?
		
	}
	
		
	@AfterClass
	public void tearDown() {
		driver.close();

	}
	private void waitFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}