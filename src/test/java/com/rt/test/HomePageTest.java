package com.rt.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rt.base.BaseClass;
import com.rt.pages.HomePage;

public class HomePageTest extends BaseClass {

	HomePage obj;
	@BeforeMethod
	public void loadObject() {
		obj = new HomePage(driver);
	}
	@Test
	public void verifyFooterLinks() throws InterruptedException {
		obj.verifyFooterLinks();
	}
	@Test
	public void VerifyHeader() throws InterruptedException {
		obj.VerifyHeader();
	}

	/*
	 * @Test public void VerifyShpNewArrivalsBannerIsPresence() throws
	 * InterruptedException { obj.verifyShpNewArrivalsBannerIsPresence(); }
	 * 
	 * @Test public void VerifyShopSaleBanner() { obj.verifyShopSaleBanner(); }
	 */
	@Test
	public void verifPageLoadTime() throws InterruptedException {
		obj.verifPageLoadTime();
	}
	@Test
	public void verifyResponsive() throws InterruptedException {
		obj.verifyResponsive();
	}
	@Test
	public void verifyTitle() {
		obj.verifyTitle();
	}
}
