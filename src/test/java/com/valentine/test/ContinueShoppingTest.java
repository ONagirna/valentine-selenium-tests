package com.valentine.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.valentine.app.AwfulValentine;
import com.valentine.app.HomePage;
import com.valentine.app.ShoppingCartPage;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.testng.Assert.*;

import java.util.Random;

@Features("Shopping")
@Stories("Add Few Items to Cart")
public class ContinueShoppingTest {
	
	private HomePage onHomePage;
	private ShoppingCartPage onShoppingCartPage;
	int randomIndex = new Random().nextInt(5) + 1;

	@BeforeClass
	public void setup() {
		onHomePage = AwfulValentine.openHomePage();
		onShoppingCartPage = onHomePage.addToCartSpecialOffer(randomIndex);

	}

	@Test
	public void testContinueShoppingRedirectsToHomePage() {
		onHomePage = onShoppingCartPage.clickContinueShoppingButton();
		assertEquals(onHomePage.getCurrentUrl(), "http://awful-valentine.com/",
				"Incorrect URL after click on 'Continue Shopping' button.");

	}

	@Test(dependsOnMethods = "testContinueShoppingRedirectsToHomePage")
	public void testAddingSecondItemToCart() {
		onShoppingCartPage = onHomePage.addToCartRecentProduct(randomIndex);
		assertEquals(onShoppingCartPage.getCurrentUrl(), "http://awful-valentine.com/store/cart/",
				"Incorrect URL after click on 'Add to Cart' button on popup.");
		String shoppingCartSummary = onShoppingCartPage.getSummary();
		assertTrue(shoppingCartSummary.startsWith("You have 2 items"), "Incorrect total number of items in the cart.");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		AwfulValentine.quit();
	}
}
