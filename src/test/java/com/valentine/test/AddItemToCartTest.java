package com.valentine.test;

import org.testng.annotations.AfterClass;

import org.testng.annotations.Test;

import com.valentine.app.AwfulValentine;
import com.valentine.app.HomePage;
import com.valentine.app.ShoppingCartPage;
import com.valentine.data.ProductDataModel;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.testng.Assert.*;

import java.util.Random;

@Features("Shopping")
@Stories("Add Item to Cart")
public class AddItemToCartTest {
	private HomePage onHomePage;
	private ShoppingCartPage onShoppingCartPage;
	private ProductDataModel testItem;

	@Test
	public void testAddCardButtonOpensPopup() {
		onHomePage = AwfulValentine.openHomePage();
		int randomIndex = new Random().nextInt(5) + 1;
		testItem = onHomePage.getSpecialOffer(randomIndex);
		onHomePage.clickAddtoCartOnSpecialOffer(randomIndex);
		
		assertTrue(onHomePage.isAddToCartPopupShown(), "'Add to cart' did not appear.");
		assertEquals(onHomePage.getProductInfoFromPopup(), testItem, "Incorrect data on popup");

	}

	
	@Test(dependsOnMethods = "testAddCardButtonOpensPopup")
	public void testAddToCartButtonOnPopupRedirectsToCartPage() {

		onShoppingCartPage = onHomePage.clickAddToCartOnPopup();
		assertEquals(onShoppingCartPage.getCurrentUrl(), "http://awful-valentine.com/store/cart/",
				"Incorrect URL after click on 'Add to Cart' button on popup");

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		AwfulValentine.quit();
	}

}


//	}
//	private void productsShouldBeEqual(ProductDataModel actual, ProductDataModel expected) {
//		String message = "";
//
//		if (!actual.getTitle().equals(expected.getTitle())) {
//			message = message.concat("Expected product title: " + expected.getTitle());
//			message = message.concat("\nActual product title: " + actual.getTitle());
//		}
//
//		if (actual.getUnitPrice() != expected.getUnitPrice()) {
//			message = message.concat("\nExpected product price: " + expected.getUnitPrice());
//			message = message.concat("\nActual product price: " + actual.getUnitPrice());
//		}
//
//		if (!message.equals(""))
//			throw new AssertionError(message);
//
