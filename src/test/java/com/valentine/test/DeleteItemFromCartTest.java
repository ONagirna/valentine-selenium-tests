package com.valentine.test;

import static org.testng.Assert.assertTrue;

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
@Stories("Delete items from the Shopping Cart")
public class DeleteItemFromCartTest {
	
	private HomePage onHomePage;
	private ShoppingCartPage onShoppingCartPage;
	private int randomIndex = new Random().nextInt(5) + 1;

	@BeforeClass
	public void setup() {
		onHomePage = AwfulValentine.openHomePage();
		onShoppingCartPage = onHomePage.addToCartSpecialOffer(randomIndex);
	}

	@Test
	public void removeItem() {
		onShoppingCartPage.clickRemoveIcon();
		
		assertTrue(onShoppingCartPage.emptyCartMessageDispayed(), "No message is displayed about empty shopping cart");

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		AwfulValentine.quit();
	}

}
