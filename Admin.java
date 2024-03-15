package fiveC_happy_flow;

import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Admin  {
	
@Test
	public void assign_to_IQC() throws Exception {
	    System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://e2e-staging-admin.5cnetwork.com");
		driver.findElement(By.id("email")).sendKeys("demo@clincalops.com");
		driver.findElement(By.id("password")).sendKeys("RadKings@3",Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@href,'/qc/coordinator')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".Icon__IconContainer-sc-1tzeys2-0.eVXKZx.CaseFilterHeaderSearch__StyledIcon-sc-10yf6wp-2.fjXPqE")).click();
	   
		 File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
	 	 FileReader FR = new FileReader(fi);
	 	 BufferedReader BR = new BufferedReader(FR);
	 	 String caseid =BR.readLine();
	    driver.findElement(By.xpath("//label[contains(@class, 'mdc-text-field') and .//span[text()='Order Id']]//input[@class='mdc-text-field__input']")).sendKeys("PL7LFPNQO0");
        driver.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
    	driver.findElement(By.cssSelector(".coordinator__QCExecutiveNameContainer-sc-1jhci00-29.jplptO")).click();
    	driver.findElement(By.id("search-ams-input")).sendKeys("demo");
    	driver.findElement(By.xpath("//span[contains(text(),'Demo IQC')]")).click();
    	Thread.sleep(2000);
    	driver.close();
	    }
	
@Test	
public void assign_to_rad() throws Exception {
	System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	driver.get("https://e2e-staging-admin.5cnetwork.com");
	driver.findElement(By.id("email")).sendKeys("demo@clincalops.com");
	driver.findElement(By.id("password")).sendKeys("RadKings@3",Keys.ENTER);
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[contains(@href,'/cases')]")).click();
	driver.findElement(By.cssSelector(".Icon__IconContainer-sc-1tzeys2-0.eVXKZx.CaseFilterHeaderSearch__StyledIcon-sc-10yf6wp-2.fjXPqE")).click();
	File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
	FileReader FR = new FileReader(fi);
	BufferedReader BR = new BufferedReader(FR);
	String caseid =BR.readLine();
	driver.findElement(By.xpath("//label[contains(@class,'mdc-text-field') and .//span[text()='Order Id']]//input[@class='mdc-text-field__input']")).sendKeys("PL7LFPNQO0");
	driver.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
	
    String[] buttonList = { "toggle--Non Reportable", "toggle--IQC Cases","toggle--OQC Cases","toggle--Reworks","toggle--Completed"};
   	driver.findElement(By.id("toggle--In Radiologist Poollabel")).click(); 	
   	for(String i : buttonList) {
  		List<WebElement> elements = driver.findElements(By.id(i));
   		WebElement firstElement = elements.get(1);
  		firstElement.click();
   	}
   	driver.findElement(By.cssSelector(".cases__DatePickerIcon-sc-1x7li8q-18.hfcwMt")).click();
    driver.findElement(By.xpath("//button[contains(text(),'CLEAR')]")).click();
    driver.findElement(By.xpath("//button[contains(text(),'DONE')]")).click();
    driver.findElement(By.cssSelector(".cases__RadNameContainer-sc-1x7li8q-14.cuwrSu")).click();
   driver.findElement(By.id("search-rads-input")).sendKeys("demo");
   Thread.sleep(2000);
   try {
   driver.findElement(By.xpath("//span[contains(text(),'Dr. Demo Radiologist (499)')]")).click();
  
   }
   catch(Exception e) {
	   driver.findElement(By.xpath("//button[contains(text(),'assign anyway')]")).click();
   }
   //Thread.sleep(2000);
   //driver.close();
}		
}
