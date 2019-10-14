package com.Interview.TestCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AirAsia {
	
	
	@Test
	public static void AirAsia() throws InterruptedException {

//		final String fromLocation = "Brunei";
//		final String toLocation = "Da Nang";
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/Lib/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.airasia.com/en/gb");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement fromclick = driver.findElement(By.xpath("//label[@for='home-origin-autocomplete-heatmap']"));
		js.executeScript("arguments[0].click();", fromclick);

		Thread.sleep(1000);

		List<WebElement> FromLocation = driver
				.findElements(By.xpath("//li[contains(@id,'home-origin-autocomplete-heatmaplist')]/span[2]"));
		
		Thread.sleep(1000);
		
		for (WebElement x : FromLocation) {

			if (x.getText().equals("Brunei")) {
				x.click();
				break;
			}

		}

		WebElement toclick = driver.findElement(By.xpath("//label[@for='home-destination-autocomplete-heatmap']"));

		js.executeScript("arguments[0].click();", toclick);
		Thread.sleep(1000);
		List<WebElement> Tolocation = driver
				.findElements(By.xpath("//li[@class='ng-tns-c44-3 ng-star-inserted']/span[2]"));

		for (WebElement y : Tolocation) {
			if (y.getText().equals("Bali")) {
				y.click();
				break;
			}
		}

		driver.findElement(By.xpath("//label[@for='trip-oneway']")).click();

		//Table 
		
//		SimpleDateFormat sim =new SimpleDateFormat("d");
//		Date date = new Date();
//		String today = sim.format(date);
//		int today_Date = Integer.parseInt(today);
//		int j = today_Date+2;
//		int k = 1;
//		String xpath = "//div[text()='"+j+"']"+"["+k+"]";
		
		 driver.findElement(By.xpath("//div[text()='16']")).click();
		
		driver.findElement(By.xpath("//button[text()=' Confirm ']")).click();
		
		driver.findElement(By.id("home-flight-search-airasia-button-inner-button-select-flight-heatmap")).click();
		
		Thread.sleep(5000);
		
		String price = driver.findElement(By.xpath("//div[@class='fare-price-select-container']/div/div/span")).getText();
		Float adultprice = Float.parseFloat(price);
	
		//verification print  
		System.out.println(price);
		
		Assert.assertEquals(price, driver.findElement(By.id("amount-text")).getText());
		
		driver.findElement(By.xpath("//button[text()=' Continue ']")).click();
		
		driver.findElement(By.xpath("//button[text()=' Continue ']")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[@class='icon-infant icon-item-infant']")).click();
		
		String HikedPrice = driver.findElement(By.id("amount-text")).getText();
		
		String HikedPriceTrim = HikedPrice.trim();
		float hikedPriceFloat = Float.parseFloat(HikedPriceTrim);
		System.out.println(hikedPriceFloat);
		float hike = hikedPriceFloat-adultprice;
		System.out.println("The hiked price when the infant added is : "+hike);
		
		driver.quit();
	}

}
