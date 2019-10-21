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
import org.testng.annotations.Test;

public class Amazon {
	
	@Test
	public void mobileSearch(){
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/Lib/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.navigate().refresh();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles",Keys.TAB);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> suggestions  = driver.findElements(By.xpath("//*[@data-alias='aps']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(suggestions));

		int suggcount = suggestions.size();
		String choice = null;
		for(int i =0;i<suggcount;i++){
			if(suggestions.get(i).getAttribute("data-keyword").contains("10000 rupees")){
				choice = suggestions.get(i).getAttribute("data-keyword") ;
				break;
			}
		}
		if(choice!=null){
			driver.findElement(By.id("twotabsearchtextbox")).clear();
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(choice,Keys.ENTER);
		}else{
			driver.findElement(By.id("twotabsearchtextbox")).clear();
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(suggestions.get(2).getAttribute("data-keyword"),Keys.ENTER);
		}
		List<String> mobileslist = new ArrayList<String>();
		int resultcount = driver.findElements(By.xpath("//*[@class='s-result-list s-search-results sg-row']//div[@class='a-section a-spacing-medium']")).size();
		for(int x=1;x<=resultcount;x++){
		String price = null;
		try {
			price = driver.findElement(By.xpath("(//*[@class='s-result-list s-search-results sg-row']//div[@class='a-section a-spacing-medium'])["+x+"]//span[@class='a-price-whole']")).getText().replaceAll(",", "");
		if(Integer.parseInt(price)<10000){
			System.out.println(price);
			//mobileslist.add(driver.findElement(By.xpath("(//*[@class='s-result-list s-search-results sg-row']//div[@class='a-section a-spacing-medium'])["+x+"]//span[@class='a-size-medium a-color-base a-text-normal']"))).getText();
		}
		} catch (Exception e) {
		}
		}
		
		//System.out.println(mobileslist);
	}

}
