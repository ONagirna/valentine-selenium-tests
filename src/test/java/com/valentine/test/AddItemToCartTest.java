package com.valentine.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.valentine.tools.Browser;

public class AddItemToCartTest {
	private WebDriver driver;

	@Test
	public void testAddCardButtonOpensPopup() {
		driver = Browser.open();
		driver.get("http://awful-valentine.com/");
		
		driver.findElement(By.cssSelector("[href='#et-offer-post-30']")).click();
		WebElement addToCartPopup = driver.findElement(By.id("fancybox-wrap"));

		Assert.assertTrue(addToCartPopup.isDisplayed());
		
		waitFor(1000);
		WebElement title = driver.findElement(By.cssSelector("#et-offer-post-30 .et_popup_title"));
		Assert.assertEquals(title.getText(), "Closeness and Togetherness", "Incorrect product title");
		waitFor(2000);
	}

	@Test(dependsOnMethods = "testAddCardButtonOpensPopup")
	public void testAddToCartButtonOnPopupRedirectsToCartPage() {
		
		waitFor(2000);
		driver.findElement(By.id("addToCart_6_2")).click();
		waitFor(2000);
		Assert.assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/store/cart/");

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
