package com.valentine.test;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.valentine.app.AwfulValentine;
import com.valentine.app.CheckoutPage;
import com.valentine.app.HomePage;
import com.valentine.app.ShoppingCartPage;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Shopping")
@Stories("Proceed from Shopping cart to Checkout page")
public class ProceedToCheckoutTest {

	private HomePage onHomePage;
	private ShoppingCartPage onShoppingCartPage;
	int randomIndex = new Random().nextInt(5) + 1;
	private CheckoutPage onCheckoutPage;

	@BeforeClass
	public void setup() {
		onHomePage = AwfulValentine.openHomePage();
		onShoppingCartPage = onHomePage.addToCartSpecialOffer(randomIndex);
	}

	@Test
	public void testProcedingToCheckout() {
		onCheckoutPage = onShoppingCartPage.clickCheckoutButton();

		assertEquals(onCheckoutPage.getCurrentUrl(), "http://awful-valentine.com/store/checkout/",
				"Incorrect URL after click on 'Checkout' button on Shopping Cart page");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		AwfulValentine.quit();
	}

}
