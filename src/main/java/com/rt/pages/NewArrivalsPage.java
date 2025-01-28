package com.rt.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.rt.utils.UtilityClass;

public class NewArrivalsPage extends UtilityClass {

	public WebDriver driver;

	public NewArrivalsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='NEW ARRIVALS']")
	WebElement newArrivalsLink;
	@FindBy(xpath = "//div[@class='sidebarBlock-headingWrapper']")
	List<WebElement> filters;

	@FindBy(xpath = "//div[@class='label-tab hidden-on-mobile']")
	WebElement sortBy;
	@FindBy(xpath = "//ul[@class='dropdown-menu list-unstyled hidden-on-mobile']/li/span")
	List<WebElement> sortByList;

	@FindBy(xpath = "//div[@class='card-product']")
	List<WebElement> listOfProducts;
	@FindBy(xpath = "//div[@class='addmore-main-section']/div[contains(text(),'More Colors')]")
	private List<WebElement> listOfMoreColorsBtn;
	@FindBy(xpath = "//div[@class='size-option-cust size-option-cust-if']/span[@class='size-option']")
	List<WebElement> listOfProductSize;
	@FindBy(xpath = "//a[contains(text(),' View Bag')]") private WebElement viewBag;
	@FindBy(xpath = "//input[@name='quantity']") private List<WebElement> listOfProductQty;
	
	@FindBy(xpath = "//div[@class='card-information__wrapper text-left']/h3/a")
	List<WebElement> listOfProductName;
	@FindBy(xpath = "//div[@class='card-price']/div/dl/div[@class='price__regular']/dd[@class='price__last']/span")
	List<WebElement> listOfProductPrice;
	
	@FindBy(xpath = "//div[@class='needsclick  kl-private-reset-css-Xuajs1']/button[@class='needsclick klaviyo-close-form go2324193863 kl-private-reset-css-Xuajs1']")
	private WebElement pop_up;

	@FindBy(xpath = "//a[@class='button button--secondary']")
	WebElement showMoreProductButton;
	@FindBy(xpath = "//div[@class='pagination-page-item pagination-page-total']")
	WebElement paginationResult;

	public void openNewArrivalsLink() {
		newArrivalsLink.click();
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

	public void loopForIsDisplayed(List<WebElement> ele, int no) {
		for (int i = no; i < ele.size(); i++) {
			Assert.assertTrue(ele.get(i).isDisplayed(), "Webelement not displayed.");
		}
	}

	public void loopForIsEnabled(List<WebElement> ele, int no) {
		for (int i = no; i < ele.size(); i++) {
			Assert.assertTrue(ele.get(i).isEnabled(), "Webelement not enabled.");
		}
	}

	/*
	 * public void verifyfiltersPresenceAndEnable() { openNewArrivalsLink();
	 * loopForIsDisplayed(filters, 0);
	 * System.out.println("Filters are present on the page.");
	 * loopForIsEnabled(filters, 0); System.out.println("Filters are enabled."); }
	 * 
	 * public void verifySortByFilter() throws InterruptedException {
	 * openNewArrivalsLink(); Assert.assertTrue(sortBy.isEnabled(),
	 * "Sort By filter is not enabled.");
	 * System.out.println("Sort By filter is enabled."); sortBy.click();
	 * Thread.sleep(10000); for (int i = 0; i < sortByList.size() - 6; i++) {
	 * Assert.assertTrue(sortByList.get(i).isDisplayed(),
	 * "Sort by filters are not displayed."); System.out.println("Sort By filter -"
	 * + sortByList.get(i).getText()); } for (int i = 4; i < sortByList.size() - 2;
	 * i++) { Assert.assertTrue(sortByList.get(i).isDisplayed(),
	 * "Sort by filters are not displayed."); System.out.println("Sort By filter -"
	 * + sortByList.get(i).getText()); } }
	 */
	public void verifyProductPricesPresence() throws InterruptedException {
		pop_up_Handle();
		openNewArrivalsLink();
		for (int i = 0; i < listOfProductPrice.size(); i++) {
			Assert.assertTrue(listOfProductPrice.get(i).isDisplayed(), "Prices of the products are not displayed.");
		}
		/*
		 * Thread.sleep(10000); showMoreProductButton.click();
		 * loopForIsDisplayed(listOfProductPrice, 20); Thread.sleep(10000);
		 * showMoreProductButton.click(); Thread.sleep(10000);
		 */
		// loopForIsDisplayed(listOfProductPrice,40);
		System.out.println("Prices of the products are displayed.");
		//System.out.println(paginationResult.getText() + " ");
	}

	public void verifyProductNamePresence() throws InterruptedException {
		pop_up_Handle();
		openNewArrivalsLink();
		for (int i = 0; i < listOfProductName.size(); i++) {
			Assert.assertTrue(listOfProductName.get(i).isDisplayed(), "Product names are not displayed.");
		}
		/*
		 * Thread.sleep(10000); showMoreProductButton.click();
		 * loopForIsDisplayed(listOfProducts, 20); Thread.sleep(10000);
		 * showMoreProductButton.click(); Thread.sleep(10000);
		 * loopForIsDisplayed(listOfProducts, 40);
		 */
		System.out.println("Product names are displayed.");
		//System.out.println(paginationResult.getText() + " ");
	}

	public void verifyProductsOnThePage() throws InterruptedException {
		pop_up_Handle();
		openNewArrivalsLink();
		for (int i = 0; i < listOfProducts.size(); i++) {
			Assert.assertTrue(listOfProducts.get(i).isDisplayed(), "Product images not displayed.");
		}
		/*
		 * Thread.sleep(10000); showMoreProductButton.click();
		 * loopForIsDisplayed(listOfProducts, 20); Thread.sleep(10000);
		 * showMoreProductButton.click(); Thread.sleep(10000);
		 * loopForIsDisplayed(listOfProducts, 40);
		 */

		System.out.println("Images of the products are displayed.");
		//System.out.println(paginationResult.getText() + " ");
	}

	public void verifyProductsQuickAddEnable() throws InterruptedException {
		pop_up_Handle();
		openNewArrivalsLink();
		//pop_up_Handle();
		Actions action = new Actions(driver);
		action.moveToElement(listOfMoreColorsBtn.get(0)).build().perform();
		Thread.sleep(10000);
		listOfProductSize.get(0).click();
		Thread.sleep(10000);
		viewBag.click();
		Thread.sleep(10000);
		String prductQty = listOfProductQty.get(0).getAttribute("value");
		Assert.assertEquals(prductQty, "1","Product quantity in cart is not equal to 1.");
		System.out.println("Quick Add option on the products card is displayed and enabled.");
		
	}

	public void verifyPageURL() throws InterruptedException {
		pop_up_Handle();
		openNewArrivalsLink();
		verifyPageURL("https://rebeccataylor.com/collections/holiday-24");
	}

	public void verifyPageTitle() throws InterruptedException {
		pop_up_Handle();
		openNewArrivalsLink();
		verifyPageTitle("Holiday '24");
	}
	public void verifyPageLoadTimeOfThePage() throws InterruptedException {
		pop_up_Handle();
		openNewArrivalsLink();
		verifyPageLoadTime();
	}

}
