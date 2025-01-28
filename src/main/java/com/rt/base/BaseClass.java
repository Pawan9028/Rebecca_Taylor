package com.rt.base;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	public WebDriver driver;

	String url = "https://rebeccataylor.com/";

	@BeforeMethod
	public WebDriver openBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		return driver;
	}

	@AfterMethod
	public void CloseBrowser() {
		driver.close();
	}
	@BeforeSuite
	public void clearOldScreenshots() {
        File directory = new File("screenshots");
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

}
