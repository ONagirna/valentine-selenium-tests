package com.valentine.test;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.valentine.app.AwfulValentine;
import com.valentine.app.HomePage;
import com.valentine.app.ShoppingCartPage;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Shopping")
@Stories("Update number of items in Shopping Cart")
public class UpdateCartItemsNumberTest {

	private HomePage onHomePage;
	private ShoppingCartPage onShoppingCartPage;
	private int randomIndex = new Random().nextInt(5) + 1;


	@BeforeClass
	public void setup() {
		onHomePage = AwfulValentine.openHomePage();
		onShoppingCartPage = onHomePage.addToCartRecentProduct(randomIndex);
	}

	@Test
	public void updateQuantity() {
		onShoppingCartPage.updateItemsQuantity();
		assertEquals(onShoppingCartPage.getQuantity(), onShoppingCartPage.newQuantity, "Incorrect number of updated");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		AwfulValentine.quit();
	}
}
