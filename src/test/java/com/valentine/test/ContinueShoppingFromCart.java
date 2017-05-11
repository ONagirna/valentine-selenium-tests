package com.valentine.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.valentine.tools.Browser;

public class ContinueShoppingFromCart {

	String mainPage = "http://awful-valentine.com/";
	String cartPage = "http://awful-valentine.com/store/cart/";
	String item1 = "Our love is special!!";
	String item2 = "Wrapped In Your Love";
	
	private WebDriver driver;

	@BeforeClass
	
	public void setup() {
		driver = Browser.open();
		driver.get(mainPage);
		
		//AwfulValentine.openHomePage();

		driver.findElement(By.cssSelector("[href='#et-entry-post-21']")).click();
		WebElement addToCartPopup = driver.findElement(By.id("fancybox-outer"));
		Assert.assertTrue(addToCartPopup.isDisplayed());

	}
	@Test
	public void testRedirectFromPopupToCartPage() {

		waitFor(3000);
		driver.findElement(By.id("addToCart_3_2")).click();
		Assert.assertEquals(driver.getCurrentUrl(), cartPage, "Redirect to incorrect page.");
		
		waitFor(1000);
		String productNameInCart = driver.findElement(By.cssSelector("#viewCartTable > tbody > tr:nth-child(1) > td:nth-child(1)")).getText();
		String ItemPriceInCart = driver.findElement(By.cssSelector("#viewCartTable>tbody>tr:nth-child(1)>td:nth-child(3)")).getText();
		String TotalPriceInCart = driver.findElement(By.cssSelector("#viewCartTable .strong.grand-total-amount.cart66-align-right")).getText();
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(productNameInCart, item1, "Incorrect product title in the cart.");		
		soft.assertEquals(ItemPriceInCart, "$68.99", "Incorrect Item Price in the cart.");
		soft.assertEquals(TotalPriceInCart, "$68.99", "Incorrect Total Price in the cart.");
		soft.assertAll();
	}
	@Test(dependsOnMethods="testRedirectFromPopupToCartPage")
	public void testContinueShopping() {

		waitFor(3000);
		driver.findElement(By.id("continueShopping")).click();

		Assert.assertEquals(driver.getCurrentUrl(), mainPage, "Redirect to incorrect page.");

	}
	@Test(dependsOnMethods = "testContinueShopping")
	public void addDifferentItemToCart() {
		driver.findElement(By.cssSelector("[href='#et-entry-post-1']")).click();
		WebElement addToCartPopup = driver.findElement(By.id("fancybox-outer"));
		Assert.assertTrue(addToCartPopup.isDisplayed());

		waitFor(1000);
		String titleOnPopup = driver.findElement(By.cssSelector("#et-entry-post-1 .et_popup_title")).getText(); 
		String priceOnPopup = driver.findElement(By.cssSelector("#cartButtonForm_2 .Cart66Price")).getText();
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(titleOnPopup, item2, "Incorrect product title on the popup.");
		soft.assertEquals(priceOnPopup, "Price: $49.99", "Incorrect product price on the popup.");
		soft.assertAll();

			
	}
	@Test(dependsOnMethods = "addDifferentItemToCart")
	public void testCartPageWithTwoItems() {

		waitFor(3000);
		driver.findElement(By.id("addToCart_2")).click();
		Assert.assertEquals(driver.getCurrentUrl(), cartPage, "Redirect to incorrect page.");
		
		waitFor(1000);
		String item1NameInCart = driver.findElement(By.cssSelector("#viewCartTable > tbody > tr:nth-child(1) > td:nth-child(1)")).getText();
		String Item1PriceInCart = driver.findElement(By.cssSelector("#viewCartTable>tbody>tr:nth-child(1)>td:nth-child(3)")).getText();
		String item2NameInCart = driver.findElement(By.cssSelector("#viewCartTable > tbody > tr:nth-child(2) > td:nth-child(1)")).getText();
		String Item2PriceInCart = driver.findElement(By.cssSelector("#viewCartTable > tbody > tr:nth-child(2) > td:nth-child(3)")).getText();
		String TotalPriceInCart = driver.findElement(By.cssSelector("#viewCartTable .strong.grand-total-amount.cart66-align-right")).getText();
		
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(item1NameInCart, item1, "Incorrect product title in the cart.");
		soft.assertEquals(Item1PriceInCart, "$68.99", "Incorrect Item Price in the cart.");
		soft.assertEquals(item2NameInCart, item2, "Incorrect product title in the cart.");
		soft.assertEquals(Item2PriceInCart, "$49.99", "Incorrect Item Price in the cart.");
		soft.assertEquals(TotalPriceInCart, "$118.98", "Incorrect Total Price in the cart.");
		
		soft.assertAll();
		
		String shoppingCartTotalItems = driver.findElement(By.id("Cart66WidgetCartEmptyAdvanced")).getText();
		
		Assert.assertTrue(shoppingCartTotalItems.startsWith("You have 2 items"), "Incorrect total number of items in the cart.");
		
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