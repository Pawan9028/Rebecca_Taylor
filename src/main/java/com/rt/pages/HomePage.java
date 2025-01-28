package com.rt.pages;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.rt.utils.UtilityClass;


public class HomePage extends UtilityClass{
	
	@FindBy(xpath = "//div[@class='img-box animate-- o-hidden']")
	List<WebElement> shoNewArrivalsbanner;
	
	@FindBy(xpath = "//a[@class='image image-adapt adaptive_height']")WebElement shopNewBanner;
	
	@FindBy(xpath = "//div[@class='needsclick  kl-private-reset-css-Xuajs1']/button[@class='needsclick klaviyo-close-form go2324193863 kl-private-reset-css-Xuajs1']")
	private WebElement pop_up;
	@FindBy(xpath = "//div[@class='footer-block__item footer-block__column footer-block__newsletter newsletter__ text-left   newletter-colm']/div/h2")
	private WebElement footerSignUpText;

	public WebDriver driver;
	

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void pop_up_Handle() {
		System.out.println("Checking for Pop_up is present or not");
		if(pop_up.isDisplayed()) {
			System.out.println("Pop_up is present.");
			pop_up.click();
			if(pop_up.isDisplayed()) {
				System.out.println("Again check for pop-up");
				pop_up.click();
				System.out.println("Pop_up is present and handled.");
		}else {
			System.out.println("Pop_up is not present.");
		}
		}
	}
	
	public void verifyShpNewArrivalsBannerIsPresence() {
		pop_up_Handle();
		for(int i=0;i<shoNewArrivalsbanner.size();i++) {
		Assert.assertTrue(shoNewArrivalsbanner.get(i).isDisplayed(),"Banner is not displayed.");
		System.out.println("Banner is displayed.");
		// Get the size of the banner
        Dimension bannerSize = shoNewArrivalsbanner.get(i).getSize();
        int bannerWidth = bannerSize.getWidth();
        int bannerHeight = bannerSize.getHeight();
     // Print the size of the banner
        System.out.println("Banner Width: " + bannerWidth);
        System.out.println("Banner Height: " + bannerHeight);
		}
	}
	public void verifyShopSaleBanner() {
		pop_up_Handle();
		Assert.assertTrue(shopNewBanner.isDisplayed(),"Banner is not displayed");
		System.out.println("Shop Sale banenr is displayed.");
		// Get the size of the banner
        Dimension bannerSize = shopNewBanner.getSize();
        int bannerWidth = bannerSize.getWidth();
        int bannerHeight = bannerSize.getHeight();
     // Print the size of the banner
        System.out.println("Banner Width: " + bannerWidth);
        System.out.println("Banner Height: " + bannerHeight);
	}
	
	 public void verifyFooterLinks() throws InterruptedException{
		 pop_up_Handle();
		 verifyFooterLinksPresenceAndRedirection();
	 }
	 
	 public void VerifyHeader() throws InterruptedException {
		 pop_up_Handle();
		 verifyHeader();
	 } 
	 
	 public void verifPageLoadTime() {
		 pop_up_Handle();
		 verifyPageLoadTime();
	 }
	 
	 public void verifyResponsive() {
		 pop_up_Handle();
		 testResponsiveDesign();
	 }
	 public void verifyTitle() {
		 pop_up_Handle();
		 verifyPageTitle("Discover Rebecca Taylor Online");
	 }
		public void VerifyPageScrollable() throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("window.scrollBy(0, 2500)");
			Thread.sleep(1000);
			js.executeScript("arguments[0].scrollIntoView();", footerSignUpText);
			//Thread.sleep(10000);
			//js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
			//js.executeScript("arguments[0].scrollIntoView();", addToCartBtn);
			//System.out.println(footerSignUpText.getText());
		}
}





   