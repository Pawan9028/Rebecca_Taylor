package com.rt.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rt.base.BaseClass;
import com.rt.pages.NewArrivalsPage;

public class NewArrivals_Test extends BaseClass{
	
	NewArrivalsPage obj;
	
	@BeforeMethod
	public void loadObject() {
		obj = new NewArrivalsPage(driver);
	}
	
	@Test
	public void verifyProductPricesPresence() throws InterruptedException {
		obj.verifyProductPricesPresence();
	}

	@Test
	public void verifyProductNamePresence() throws InterruptedException {
		obj.verifyProductNamePresence();
	}

	@Test
	public void verifyProductsOnThePage() throws InterruptedException {
		obj.verifyProductsOnThePage();
	}

	@Test
	public void verifyProductsQuickAddEnable() throws InterruptedException {
		obj.verifyProductsQuickAddEnable();
	}
	@Test
	public void verifyPageURL() throws InterruptedException {
		obj.verifyPageURL();
	}
	@Test
	public void verifyPageTitle() throws InterruptedException{
		obj.verifyPageTitle();
	}
	@Test
	public void verifyPageLoadTime() throws InterruptedException{
		obj.verifyPageLoadTimeOfThePage();
	}


}
