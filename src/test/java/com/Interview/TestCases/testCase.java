package com.Interview.TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testCase {

	@Test
	public void FlipkartTest1() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/Lib/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[text()='✕']")).click();

		driver.findElement(By.name("q")).sendKeys("mobiles", Keys.ENTER);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='col col-7-12']/div[1]")));

		String text = driver.findElement(By.className("_2yAnYN")).getText();

		String temp = text.substring(12, 14);

		int totalPageCount = Integer.parseInt(temp);

		List<WebElement> allElement = driver.findElements(By.xpath("//div[@class='col col-7-12']/div[1]"));

		List<WebElement> redmi = new ArrayList<WebElement>();

		for (WebElement x : allElement) {
			if (x.getText().contains("Redmi")) {
				redmi.add(x);
			}

		}

		System.out.println("The below are the available redmi mobiles in the first page");
		int j = 1;
		for (int i = 0; i < redmi.size(); i++) {
			System.out.println(j+" : "+redmi.get(i).getText());
			j++;
		}
		Assert.assertEquals(totalPageCount, allElement.size());
		
		
		driver.quit();
	}

}
