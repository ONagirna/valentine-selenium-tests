package com.valentine.app;

import org.openqa.selenium.WebDriver;

import com.valentine.tools.Browser;

import ru.yandex.qatools.allure.annotations.Step;

public class AwfulValentine {
	private static final String BASE_URL = "http://awful-valentine.com/";

	private static WebDriver driver;

	@Step("Open Home Page by URL: " + BASE_URL)
	public static HomePage openHomePage() {
		driver = Browser.open();
		driver.manage().window().maximize();
		driver.get(BASE_URL);
		return new HomePage(driver);

	}

	@Step("Close Browser")
	public static void quit() {
		if (driver != null)
			driver.quit();
		driver = null;

	}

}
