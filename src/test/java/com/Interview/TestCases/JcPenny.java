package com.Interview.TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JcPenny {

	static int m = 1;

	@Test
	public static void JcPennytest() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/Lib/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("https://www.jcpenney.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement deals = driver.findElement(By.xpath("//div[text()='Deals']"));

		Actions acc = new Actions(driver);

		acc.moveToElement(deals).perform();

		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[text()='Deals Under $15']")));

		driver.findElement(By.xpath("//a[text()='Deals Under $15']")).click();

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//img[@title='Mens']")));

		driver.findElement(By.xpath("//img[@title='Mens']")).click();

		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[@data-automation-id='pagination-count']")));

		String Totaltext = driver.findElement(By.xpath("//div[@data-automation-id='pagination-count']")).getText();

		String[] splitTotaltext = Totaltext.split("-");

		String toltaltextFinal = splitTotaltext[1].substring(1, 3);
		int totaltextFinalint = Integer.parseInt(toltaltextFinal);

		List<WebElement> allproducts = driver.findElements(By.xpath("//span[contains(text(),'$')]"));

		List<String> productWithSinglepriceTag = new ArrayList<String>();
		List<String> productWithvariablepriceTag = new ArrayList<String>();
		List<String> productWithvariablepriceTagMin = new ArrayList<String>();
		List<String> productWithvariablepriceTagMax = new ArrayList<String>();

		for (int i = 4; i < allproducts.size(); i++) {

			char[] charArray = allproducts.get(i).getText().toCharArray();

			if (charArray.length <= 6) {

				productWithSinglepriceTag.add(allproducts.get(i).getText().replace("$", "").trim());

			}

			else {
				productWithvariablepriceTag.add(allproducts.get(i).getText());

				for (String string : productWithvariablepriceTag) {
					String[] splitstring = string.split("-");
					productWithvariablepriceTagMin.add(splitstring[0].replace("$", "").trim());
					productWithvariablepriceTagMax.add(splitstring[1].replace(" ", "").replace("$", "").trim());
				}
			}

		}

		//printing the Max variable price tag values that are exceeding 15	
		System.out.println("the number of variabe price tag values that are exceeding $15.00 are ");
		
			for (String string2 : productWithvariablepriceTagMax) {
			if (Float.parseFloat(string2) > 15.00f) {
				System.out.println(m + " : " + "$" + Float.parseFloat(string2));
				m++;
			}
			
			// verifying the size of the products listed in the page

			Assert.assertEquals(productWithSinglepriceTag.size() + productWithvariablepriceTag.size(),
					totaltextFinalint);

			// verifying the price tag is less than 15

			for (String string : productWithSinglepriceTag) {

				Assert.assertTrue(Float.parseFloat(string) < 15.00f);

			}
			for (String string1 : productWithvariablepriceTagMin) {

				Assert.assertTrue(Float.parseFloat(string1) < 15.00f);

			}

		}

		driver.quit();
	}
}
