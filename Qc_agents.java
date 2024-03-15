package fiveC_happy_flow;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Qc_agents {
	
	 @Test
    public void iqc_reportable() throws Exception {
		 System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
			WebDriver driver = new ChromeDriver();
			driver.get("https://e2e-staging-admin.5cnetwork.com/");
		    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.findElement(By.id("email")).sendKeys("demo@iqc.com");
			driver.findElement(By.id("password")).sendKeys("RadKings@3",Keys.ENTER);
			Thread.sleep(2000);
		    driver.findElement(By.cssSelector(".Button-sc-9irl08-0.qc__DatePickerButton-sc-b06d1u-9.fbIlgK")).click();
		    driver.findElement(By.xpath("//button[contains(text(),'CLEAR')]")).click();
		    driver.findElement(By.xpath("//button[contains(text(),'DONE')]")).click();
			//Reading the case id from my file 
		    File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
			FileReader FR = new FileReader(fi);
			BufferedReader BR = new BufferedReader(FR);
			String caseid =BR.readLine();
		    //below lines of code is used to open the case based on give caseid value 
			WebElement spanElementWithValue = driver.findElement(By.xpath("//span[contains(text(), '" + caseid + "')]"));
		    WebElement lastSpanElement = spanElementWithValue.findElement(By.xpath("./ancestor::div[contains(@class, 'qc__TableBodyRow')]/div[last()]/span"));
		    lastSpanElement.click();
		    String parentwindow =driver.getWindowHandle();
		    System.out.println("IQC window id :"+parentwindow);
		    Set <String> handels =driver.getWindowHandles();
		    for (String handel : handels ) {
			if (!handel.equals(parentwindow)) {
				driver.switchTo().window(handel);
				driver.findElement(By.id("toggle--Take break after this caselabel")).click();				
		    	driver.findElement(By.id("toggle--No")).click();
		    	driver.findElement(By.id("remarks")).sendKeys("Testing");
				driver.findElement(By.xpath("//button[contains(@data-cy,'reportable-button')]")).click();
				//driver.findElement(By.xpath("//button[contains(text(),'Take Break')]")).click();
				Thread.sleep(7000);
				driver.findElement(By.xpath("//div[contains(text(),'mark case as reportable ')]")).click();
				}
			}
		    Thread.sleep(2000);
		    driver.close();
	 }
	 
	 
		@Test
		public void oqc_approvel() throws Exception {
			 System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
				WebDriver driver = new ChromeDriver();
				driver.get("https://e2e-staging-admin.5cnetwork.com/");
			    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.findElement(By.id("email")).sendKeys("demo@iqc.com");
				driver.findElement(By.id("password")).sendKeys("RadKings@3",Keys.ENTER);
				Thread.sleep(2000);
			    driver.findElement(By.cssSelector(".Button-sc-9irl08-0.qc__DatePickerButton-sc-b06d1u-9.fbIlgK")).click();
			    driver.findElement(By.xpath("//button[contains(text(),'CLEAR')]")).click();
			    driver.findElement(By.xpath("//button[contains(text(),'DONE')]")).click();
			    
		    File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
			FileReader FR = new FileReader(fi);
			BufferedReader BR = new BufferedReader(FR);
			String caseid =BR.readLine();
		    //below lines of code is used to open the case based on give caseid value 
		    WebElement spanElementWithValue = driver.findElement((By.xpath("//span[contains(text(), '" + caseid + "')]")));
		    WebElement lastSpanElement = spanElementWithValue.findElement(By.xpath("./ancestor::div[contains(@class, 'qc__TableBodyRow')]/div[last()]/span"));
		    lastSpanElement.click();
			String parentwindow =driver.getWindowHandle();
			System.out.println("IQC window id :"+parentwindow);
		    Set <String> handels =driver.getWindowHandles();
			for (String handel : handels ) {
		            if (!handel.equals(parentwindow)) {
					driver.switchTo().window(handel);
					try {
						driver.findElement(By.id("toggle--Yes")).click();
						}
						catch(Exception ee) {
							System.out.println("QA Correlation is not found");
						}
					driver.findElement(By.id("toggle--Approvelabel")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//button[contains(text(),'Accept Case (1)')]")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//button[contains(text(),'Submit & take break')]")).click();
					//driver.findElement(By.xpath("//button[contains(text(),'Take Break')]")).click();
					}
	            }
		}
		
}
