package com.azurepricing.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.azurepricing.Dao.AzurePricingDao;
import com.azurepricing.Utils.AzurePricingUtils;
import com.azurepricing.dto.AzurePricingAHBDto;
import com.azurepricing.dto.AzurePricingWAHBDto;
import com.azurepricing.model.AzurePricing;

@Service
public class AzurePricingService {	

	public HashMap<String, AzurePricingAHBDto> GetPrices(String TimeUnit,String OS,String Region,String currency) throws InterruptedException {
		HashMap<String, AzurePricingAHBDto> azureData = new HashMap<String,AzurePricingAHBDto>();
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver wd = new ChromeDriver(options);
		String baseUrl = "https://azure.microsoft.com/en-us/pricing/details/virtual-machines/windows/";
		wd.get(baseUrl);
		Thread.sleep(15000);
		Select timeUnit= new Select(wd.findElement(By.name("unitPricing")));
		timeUnit.selectByValue(TimeUnit);
		Select Os= new Select(wd.findElement(By.name("softwareOSType")));
		Os.selectByValue(OS);
		Select region= new Select(wd.findElement(By.name("region")));
		region.selectByValue(Region);
		Select Currency= new Select(wd.findElement(By.name("currency")));
		Currency.selectByValue(currency);
		try {
		WebElement button = wd.findElement(By.id("isAhb"));
		}catch(NoSuchElementException e) {
			List<WebElement> tables = wd.findElements(By.cssSelector(".data-table--pricing.table > table > tbody > tr"));
			for(int i=0;i<tables.size();i++)
			{
				System.out.println("table "+i+" is in progress for AHB(No AHB)");
				AzurePricingAHBDto rowData = new AzurePricingAHBDto();
				rowData.setInstance(tables.get(i).findElement(By.cssSelector("td:nth-child(1)")).getText());
				rowData.setPayAsYouGoAHB("N/A");
				rowData.setOneYearReservedAHB("N/A");
				rowData.setThreeYearReservedAHB("N/A");
				rowData.setSpotAHB("N/A");
				azureData.put(tables.get(i).findElement(By.cssSelector("td:nth-child(1)")).getText(), rowData);
			}
			return azureData;
		}
		Thread.sleep(15000);
		List<WebElement> tables = wd.findElements(By.cssSelector(".data-table--pricing.table > table"));
		for (int i = 0; i < tables.size(); i++) {
			System.out.println("table "+i+" is in progress for AHB");
			List<WebElement> tableColumns = tables.get(i).findElements(By.cssSelector("thead>tr>th"));
			if (tableColumns.size() == 9) {
				List<WebElement> tableRows = tables.get(i).findElements(By.cssSelector("tbody>tr"));
				for (int j = 0; j < tableRows.size(); j++) {
					AzurePricingAHBDto rowData = new AzurePricingAHBDto();
					rowData.setInstance(tableRows.get(j).findElement(By.cssSelector("td:nth-child(1)")).getText());
					try {
						rowData.setPayAsYouGoAHB(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(5)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setPayAsYouGoAHB("N/A");
					}
					try {
						rowData.setOneYearReservedAHB(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(6)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setOneYearReservedAHB("N/A");
					}
					try {
						rowData.setThreeYearReservedAHB(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(7)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setThreeYearReservedAHB("N/A");
					}
					try {
						rowData.setSpotAHB(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(8)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setSpotAHB("N/A");
					}
					azureData.put(tableRows.get(j).findElement(By.cssSelector("td:nth-child(1)")).getText(), rowData);
				}
			} else {
				List<WebElement> tableRows = tables.get(i).findElements(By.cssSelector("tbody>tr"));
				for (int j = 0; j < tableRows.size(); j++) {
					AzurePricingAHBDto rowData = new AzurePricingAHBDto();
					rowData.setInstance(tableRows.get(j).findElement(By.cssSelector("td:nth-child(1)")).getText());
					try {
						rowData.setPayAsYouGoAHB(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(6)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setPayAsYouGoAHB("N/A");
					}
					try {
						rowData.setOneYearReservedAHB(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(7)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setOneYearReservedAHB("N/A");
					}
					try {
						rowData.setThreeYearReservedAHB(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(8)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setThreeYearReservedAHB("N/A");
					}
					try {
						rowData.setSpotAHB(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(9)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setSpotAHB("N/A");
					}
					azureData.put(tableRows.get(j).findElement(By.cssSelector("td:nth-child(1)")).getText(), rowData);
				}
			}
		}
		System.out.println("AHB Completed");
		wd.quit();
		return azureData;
	}

	public HashMap<String, AzurePricingWAHBDto> GetPricesWAHB(String TimeUnit,String OS,String Region,String currency) throws InterruptedException {
		HashMap<String, AzurePricingWAHBDto> azureData1 = new HashMap<String,AzurePricingWAHBDto>();
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver wd1 = new ChromeDriver(options);
		wd1.manage().window().maximize();
		String baseUrl = "https://azure.microsoft.com/en-us/pricing/details/virtual-machines/windows/";
		wd1.get(baseUrl);
		Thread.sleep(10000);
		Select timeUnit= new Select(wd1.findElement(By.name("unitPricing")));
		timeUnit.selectByValue(TimeUnit);
		Select Os= new Select(wd1.findElement(By.name("softwareOSType")));
		Os.selectByValue(OS);
		Select region= new Select(wd1.findElement(By.name("region")));
		region.selectByValue(Region);
		Select Currency= new Select(wd1.findElement(By.name("currency")));
		Currency.selectByValue(currency);
		
		try {
		WebElement button = wd1.findElement(By.id("isAhb"));
		JavascriptExecutor executor = (JavascriptExecutor)wd1;
		executor.executeScript("arguments[0].click();",button);
		Thread.sleep(10000);
		}catch(NoSuchElementException e) {
		}
		
		List<WebElement> tables = wd1.findElements(By.cssSelector(".data-table--pricing.table > table"));
		for (int i = 0; i < tables.size(); i++) {
			System.out.println("table "+i+" is in progress for WAHB");
			List<WebElement> tableColumns = tables.get(i).findElements(By.cssSelector("thead>tr>th"));
			if (tableColumns.size() == 9) {
				List<WebElement> tableRows = tables.get(i).findElements(By.cssSelector("tbody>tr"));
				for (int j = 0; j < tableRows.size(); j++) {
					AzurePricingWAHBDto rowData = new AzurePricingWAHBDto();
					rowData.setRegion(Region);
					rowData.setInstance(tableRows.get(j).findElement(By.cssSelector("td:nth-child(1)")).getText());
					rowData.setCores(tableRows.get(j).findElement(By.cssSelector("td:nth-child(2)")).getText());
					rowData.setRam(tableRows.get(j).findElement(By.cssSelector("td:nth-child(3)")).getText());
					rowData.setStorage(tableRows.get(j).findElement(By.cssSelector("td:nth-child(4)")).getText());
					try {
						rowData.setPayAsYouGo(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(5)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setPayAsYouGo("N/A");
					}
					try {
						rowData.setOneYearReserved(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(6)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setOneYearReserved("N/A");
					}
					try {
						rowData.setThreeYearReserved(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(7)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setThreeYearReserved("N/A");
					}
					try {
						rowData.setSpot(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(8)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setSpot("N/A");
					}
					azureData1.put(tableRows.get(j).findElement(By.cssSelector("td:nth-child(1)")).getText(), rowData);;
				}
			} else {
				List<WebElement> tableRows = tables.get(i).findElements(By.cssSelector("tbody>tr"));
				for (int j = 0; j < tableRows.size(); j++) {
					AzurePricingWAHBDto rowData = new AzurePricingWAHBDto();
					rowData.setRegion(Region);
					rowData.setInstance(tableRows.get(j).findElement(By.cssSelector("td:nth-child(1)")).getText());
					rowData.setCores(tableRows.get(j).findElement(By.cssSelector("td:nth-child(2)")).getText());
					rowData.setRam(tableRows.get(j).findElement(By.cssSelector("td:nth-child(3)")).getText());
					rowData.setStorage(tableRows.get(j).findElement(By.cssSelector("td:nth-child(4)")).getText());
					try {
						rowData.setPayAsYouGo(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(6)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setPayAsYouGo("N/A");
					}
					try {
						rowData.setOneYearReserved(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(7)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setOneYearReserved("N/A");
					}
					try {
						rowData.setThreeYearReserved(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(8)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setThreeYearReserved("N/A");
					}
					try {
						rowData.setSpot(AzurePricingUtils.parseCost(
								tableRows.get(j).findElement(By.cssSelector("td:nth-child(9)>p>span")).getText()));
					} catch (NoSuchElementException e) {
						rowData.setSpot("N/A");
					}
					azureData1.put(tableRows.get(j).findElement(By.cssSelector("td:nth-child(1)")).getText(), rowData);
				}
			}
		}
		wd1.quit();
		System.out.println("WAHB Completed");
	return azureData1;
	}
}
