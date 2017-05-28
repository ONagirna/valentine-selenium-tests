package com.valentine.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;


public class ShoppingCartPage {
	public String newQuantity = "15"; // Random??? 

	@FindBy(id = "continueShopping")
	private WebElement continueShoppingButton;

	@FindBy(id = "Cart66WidgetCartEmptyAdvanced")
	private WebElement summary;

	@FindBy(id = "Cart66CheckoutButton")
	private WebElement checkoutButton;

	@FindBy(css = ".itemQuantity")
	private WebElement quantityField;

	@FindBy(css = ".Cart66UpdateTotalButton")
	private WebElement updateTotalButton;
	
	@FindBy(css = "img[alt='Remove Item']")
	private WebElement removeIcon;
	
	@FindBy(id = "emptyCartMsg")
	private WebElement emptyCartMessage;
	
	private WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		new WebDriverWait(driver, 10).until(urlContains("cart"));
		PageFactory.initElements(driver, this);
	}

	@Step("Click on Continue Shopping button")
	public HomePage clickContinueShoppingButton() {
		continueShoppingButton.click();
		return new HomePage(driver);
	}

	@Step("Read Summary")
	@Attachment("Summary")
	public String getSummary() {
		return summary.getText();
	}

	@Step("Read current URL")
	@Attachment("URL")
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Step("Click on Checkout button")
	public CheckoutPage clickCheckoutButton() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
	@Step("Check the default number of items in the cart")
	public String getQuantity() {
		return quantityField.getAttribute("value");

	}
	
	@Step("Enter new number into Quantity field and click Update Total button in the cart")
	public ShoppingCartPage updateItemsQuantity() {
		quantityField.clear();
		quantityField.sendKeys(newQuantity);
		updateTotalButton.click();
	return new ShoppingCartPage(driver);
	}
	
	@Step("Click on Remove button")
	public ShoppingCartPage clickRemoveIcon() {
		return new ShoppingCartPage(driver);
	}
	
	@Step("Check that item is removed from the shopping cart")
	public boolean emptyCartMessageDispayed() {
		removeIcon.click();
		return emptyCartMessage.isDisplayed();
	}
	

}






















