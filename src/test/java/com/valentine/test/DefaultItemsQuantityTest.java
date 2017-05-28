package com.valentine.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.valentine.app.AwfulValentine;
import com.valentine.app.HomePage;
import com.valentine.app.ShoppingCartPage;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Shopping")
@Stories("Verify default quantity of items in the cart")
public class DefaultItemsQuantityTest {

	private HomePage onHomePage;
	private ShoppingCartPage onShoppingCartPage;

	@BeforeClass
	public void setup() {
		onHomePage = AwfulValentine.openHomePage();
		onShoppingCartPage = onHomePage.addToCartSpecialOffer(5);

	}

	@Test
	public void checkDefaultQuantityValue() {
		assertEquals(onShoppingCartPage.getQuantity(), "1", "Incorrect default quantity of items in the cart");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		AwfulValentine.quit();
	}

}
