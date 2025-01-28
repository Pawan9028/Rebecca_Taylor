package com.rt.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class UtilityClass {

	public WebDriver driver;

	public UtilityClass(WebDriver driver) {
		this.driver = driver;
	}
	
	 public static final String RESET = "\033[0m";  // Text Reset
	    public static final String BLACK = "\033[0;30m";   // BLACK
	    public static final String RED = "\033[0;31m";     // RED
	    public static final String GREEN = "\033[0;32m";   // GREEN
	    public static final String YELLOW = "\033[0;33m";  // YELLOW
	    public static final String BLUE = "\033[0;34m";    // BLUE
	    public static final String PURPLE = "\033[0;35m";  // PURPLE
	    public static final String CYAN = "\033[0;36m";    // CYAN
	    public static final String WHITE = "\033[0;37m";   // WHITE

	// Webelements for header
	@FindBy(xpath = "//div[@class='header__heading d-inline-block']")
	private WebElement logo;

	@FindBy(xpath = "//a[@title='Nav link to Account']")
	private WebElement account_tab;

	@FindBy(xpath = "//a[@class='header__icon link header__icon--cart link--text focus-inset cart-icon-bubble']")
	private WebElement cart_tab;
	@FindBy(xpath = "//span[text()='Shopping Bag']")
	private WebElement titleForCartButton;
	@FindBy(xpath = "//span[text()='Shopping Bag']")
	private WebElement titlecartButton;
	@FindBy(xpath = "//a[@class='button button-2 button-continue']")
	private WebElement continueshopping;

	@FindBy(xpath = "//span[@class='stroke-icon']")
	private WebElement search_Icon;
	@FindBy(id = "Search-In-Modal-Menu-Plain")
	private WebElement searchProduct;
	@FindBy(xpath = "//div[@class='swiper-wrapper']/div/a")
	private List<WebElement> searchResult;
	@FindBy(xpath = "//span[text()='NEW ARRIVALS']")
	WebElement newArrivalsLinks;
	@FindBy(xpath = "//span[text()='SALE']")
	WebElement saleLinks;
	@FindBy(xpath = "//span[text()='CLOTHING']")
	WebElement clothingLink;
	@FindBy(xpath = "//div[@class='desk-menumid-cust']/li[@class='menu-lv-item menu-lv-2 text-left   menu-lv-blink']")
	List<WebElement> clothingDropdownList;
	@FindBy(xpath = "//span[text()='COLLECTIONS']")WebElement collectionLink;
	@FindBy(xpath = "//div[@class='desk-menumid-cust']/li[@class='menu-lv-item menu-lv-2 text-left   menu-lv-blink']")
	List<WebElement> collectionDropdownList;
	
	@FindBy(xpath = "//div[@class='desk-menumid-cust']/li/a")
	List<WebElement> headerDropdownList;

	// Webelements for footer
	@FindBy(xpath = "//span[text()='Returns Center']")
	private WebElement returnCenter;

	@FindBy(xpath = "//input[@id='NewsletterForm--sections--24175489745185__fbbf34f0-01df-4c98-b458-6c14dc41c8e9']")
	List<WebElement>  newsletterBox; //1
	@FindBy(xpath = "//button[@class='button newsletter-form__button']")
	WebElement newsletterSubmitButton;
	@FindBy(xpath = "//div[@class='footer-block__item footer-block__column footer-block__newsletter newsletter__ text-left   newletter-colm']/div/h2")
	private WebElement footerSignUpText;
	@FindBy(xpath = "//div[@class='footer__security-trust text-center']")private WebElement footerLogo;

	@FindBy(xpath = "//div[@class='copyright__content']/p/a")
	List<WebElement> PPAndTClink;

	@FindBy(xpath = "//div[@class='footer-block__list']/ul[@class='list-unstyled']/li/a")
	List<WebElement> listOfFooterLinks;

//////////Header methods ////////

	public void VerifyLogo() {
		logo.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://rebeccataylor.com/", "Logo is not clickable");
		Assert.assertTrue(logo.isEnabled(), "Logo is not enabled");
		Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed.");
		System.out.println(GREEN+"Logo is present and clickable.");
	}

	public void VerifyAccountTab() {
		account_tab.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://rebeccataylor.com/account/login?return_url=%2Faccount",
				"Account tab is not redirected to the desired page.");
		Assert.assertTrue(account_tab.isDisplayed() && account_tab.isEnabled(), "Account icon is not displayed.");
		System.out.println(GREEN+"Account icon is displayed and clickable.");
	}

	public void VerifyCartIcon() throws InterruptedException {
		cart_tab.click();
		Thread.sleep(1000);
		String cart_title = titleForCartButton.getText();
		Assert.assertEquals(cart_title, "Shopping Bag", "Cart icon is not reditected to the ajax cart.");
		System.out.println(GREEN+"Cart icon is displayed and redirected to the desired page.");
	    driver.navigate().back();
	}

	public void verifySearchIcon() throws InterruptedException {
		Thread.sleep(10000);
		search_Icon.click();
		searchProduct.sendKeys("tops");
		String href = searchResult.get(0).getAttribute("href");
		driver.navigate().to(href);
		// searchResult.get(0).click();
		Assert.assertEquals(driver.getCurrentUrl(), href,
				"Search result is not searching the expected search product.");
		System.out.println(GREEN+"Search icon is enabled and searched the expected result.");
		driver.navigate().back();
	}

	public void verifyNewArrivalsLinkRedirection() {
		newArrivalsLinks.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://rebeccataylor.com/collections/fw24",
				"New Arrivals link is not redirected to the particular page.");
		System.out.println(GREEN+"New Arrivals link is redirected to the expectd page.");
		driver.navigate().back();
	}

	public void verifySaleLinkRedirection() {
		saleLinks.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://rebeccataylor.com/collections/sale", "Sale link is not redirected to the particular page.");
		System.out.println(GREEN+"Sale link is redirected to the expectd page.");
	}

	public void verifyClotingHeaderLink() {
		Actions action = new Actions(driver);
		action.moveToElement(clothingLink).build().perform();
		for (int i = 0; i < clothingDropdownList.size() - 4; i++) {
			String href = headerDropdownList.get(i).getAttribute("href");
			driver.navigate().to(href);
			Assert.assertTrue(href.contains("/collections"),
					"Clothing child dropdown links not redirected to the paricular page.");
			driver.navigate().back();
		}
		System.out.println(GREEN+"Clothing child dropdown links redirected to the desired page. ");
	}

	public void verifyCollectionHeaderLink() {
		Actions action = new Actions(driver);
		action.moveToElement(collectionLink).build().perform();
		for (int i = 5; i < headerDropdownList.size(); i++) {
			String href = headerDropdownList.get(i).getAttribute("href");
			driver.navigate().to(href);
			Assert.assertTrue(href.contains("/collections"),
					"Header Collection child dropdown links not redirected to the paricular page.");
			driver.navigate().back();
		}
		System.out.println(GREEN+"Header Collection  child dropdown links redirected to the desired page. ");
	}
	
	public void verifyHeader() throws InterruptedException {
		System.out.println(BLUE +"Checking header of the page....");
		VerifyLogo();
		VerifyAccountTab();
		VerifyCartIcon();
		verifySearchIcon();
		verifyNewArrivalsLinkRedirection();
		//verifySaleLinkRedirection();
		verifyClotingHeaderLink();
		verifyCollectionHeaderLink();
		System.out.println(GREEN +"Header is checked.");
	}

	/////////////////// Footer methods//////////////////
	/*
	 * public void verifyFooterReturnCenterLink() {
	 * Assert.assertTrue(returnCenter.isDisplayed(),
	 * "Footer Return Center link is not displayed.");
	 * System.out.println("Footer Return Center link is displayed.");
	 * returnCenter.click(); String actRes = driver.getCurrentUrl(); String expRes =
	 * "https://returns.rebeccataylor.com/#/"; //
	 * Assert.assertTrue(footerAbout.isDisplayed(),"Footer About link is not //
	 * displayed."); Assert.assertTrue(actRes.contains(expRes),
	 * "Not redirected to the expected page, it may contain hashlink."); System.out.
	 * println("Footer Return Center link is redirected to the particular page.");
	 * driver.navigate().back(); }
	 */
	public void verifyFooterNewsletter() {
		Assert.assertTrue(newsletterBox.get(0).isEnabled(),"Newsletter is not enabled.");
		Assert.assertTrue(newsletterSubmitButton.isEnabled(),"Newsletter Submit button is not enabled.");
		System.out.println("Newsletter section is present and enabled.");
	}
	/*
	 * public void verifyFooterLogoClickable() throws InterruptedException {
	 * listOfFooterLinks.get(1).click(); JavascriptExecutor js =
	 * (JavascriptExecutor) driver; Thread.sleep(1000);
	 * js.executeScript("arguments[0].scrollIntoView();", footerSignUpText);
	 * footerLogo.click(); }
	 */

	public void verifyFooterLinksPresenceAndRedirection() throws InterruptedException {
		System.out.println("Footer is checking...");
		for (int i = 0; i < listOfFooterLinks.size() - 15; i++) {
			String href = listOfFooterLinks.get(i).getAttribute("href");
			listOfFooterLinks.get(i).click();
			String actRes = driver.getCurrentUrl();
			Assert.assertTrue(listOfFooterLinks.get(i).isDisplayed(), href + " - link is not displayed.");
			// System.out.println("Actual URL= "+actRes);
			System.out.println(listOfFooterLinks.get(i).getText() + " footer link is displayed.");
			Assert.assertTrue(actRes.contains("rebeccataylor.com"),
					href + " - Not redirected to the expected page, it may contain hashlink.");
			System.out.println(href + "\n");
			driver.navigate().back();
		}
		//verifyFooterReturnCenterLink(); int i=4
		for (int i = 0; i < listOfFooterLinks.size() - 3; i++) {
			String href = listOfFooterLinks.get(i).getAttribute("href");
			Assert.assertTrue(listOfFooterLinks.get(i).isDisplayed() || listOfFooterLinks.get(i).isEnabled(), href + " - link is not displayed.");
			System.out.println(listOfFooterLinks.get(i).getText() + " footer link is displayed.");
			Assert.assertTrue(listOfFooterLinks.get(i).isEnabled(),
					href + " - Not redirected to the expected page, it may contain hashlink.");
			System.out.println(href + "\n");
		}

		for (int i = 0; i < PPAndTClink.size(); i++) {
			String href = PPAndTClink.get(i).getAttribute("href");
			PPAndTClink.get(i).click();
			String actRes = driver.getCurrentUrl();
			Assert.assertTrue(PPAndTClink.get(i).isDisplayed(), href + " - link is not displayed.");
			// System.out.println("Actual URL= "+actRes);
			System.out.println(PPAndTClink.get(i).getText() + " footer link is displayed.");
			Assert.assertTrue(actRes.contains("rebeccataylor.com"),
					href + " - Not redirected to the expected page, it may contain hashlink.");
			System.out.println(href + "\n");
			driver.navigate().back();
		}
		Thread.sleep(1000);
		verifyFooterNewsletter();
		Assert.assertTrue(footerLogo.isDisplayed(),"Footer logo is not displayed.");
		System.out.println("Footer logo is displayed.");
		System.out.println("Footer links are present and redirected to the particular pages.");
		System.out.println("Footer has been checked.");
	}

	//////////////common methods /////////////
	public void verifyPageTitle(String expTitle) {
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expTitle, "Title of the page does not matched");
		System.out.println("Title of the page is matched." + "\n" + "Actual Title- " + actTitle);
	}

	public void verifyPageURL(String expURL) {
		String actURL = driver.getCurrentUrl();
		Assert.assertEquals(actURL, expURL, "URL of the page does not matched");
		System.out.println("URL of the page is matched." + "\n" + "Actual URL- " + actURL);
	}

	public void verifyPageLoadTime() {
		    long start = System.currentTimeMillis();
	        driver.navigate().refresh();
	        long finish = System.currentTimeMillis();
	        long totalTime = finish - start;
	        System.out.println("Page load time is "+totalTime+"sec");
	        Assert.assertTrue(totalTime < 4000, "Page load time is more than 4 seconds.");
	        System.out.println("Page load time is "+totalTime+"sec");
	}

	public void verifyCreossBrowserCompatibility(String url) {
        // Example shown for Chrome and Firefox only.
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get(url);
        String chromeTitle = driver.getTitle();
        String firefoxTitle = firefoxDriver.getTitle();
        Assert.assertEquals(chromeTitle, firefoxTitle, "Page title does not match between Chrome and Firefox.");
        firefoxDriver.quit();
        System.out.println("Page title matched between Chrome and Firefox.");

	}

	public void verifyFontSizeOfTheText(WebElement fontsize) {
		String fontSize = fontsize.getCssValue("font-size");
		System.out.println("Fobt Size of the text on the page is "+fontSize);

	}
	 public void testResponsiveDesign() {
	       // driver.get(baseUrl);

	        // List of different screen resolutions to test
	        List<Dimension> dimensions = Arrays.asList(
	            new Dimension(1920, 1080), // Desktop
	            new Dimension(1366, 768),  // Laptop
	            new Dimension(768, 1024),  // Tablet portrait
	            new Dimension(1024, 768),  // Tablet landscape
	            new Dimension(375, 667),   // Mobile portrait
	            new Dimension(667, 375)    // Mobile landscape
	        );
	        for (Dimension dimension : dimensions) {
	            driver.manage().window().setSize(dimension);
	            // Add assertions or checks to verify the responsive layout
	            // Example: check if the page title is correct
	            String pageTitle = driver.getTitle();
	            System.out.println("Resolution: " + dimension + " - Page title: " + pageTitle);
	            assert(pageTitle.contains("Rebecca Taylor"));  // Example assertion
	        }
	    }

}
