package fiveC_happy_flow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Ttest {
	
	@Test
	public void reportable() throws Exception {
		
		 // Set the path to your chromedriver executable
      System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
  
      // Create ChromeOptions instance
      ChromeOptions opt = new ChromeOptions();

      // Add the necessary arguments to disable clipboard and handle clipboard permission
      opt.addArguments("disable-clipboard");

      // Set up clipboard permission handling
      setClipboardPermission(opt);

      WebDriver driver = new ChromeDriver(opt);
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      driver.get("https://e2e-staging-rads.5cnetwork.com/");
      driver.manage().window().maximize();
      driver.findElement(By.id("email")).sendKeys("demo@radiologist.com");
      driver.findElement(By.id("password")).sendKeys("demo123",Keys.ENTER);
      Thread.sleep(2000);
      driver.findElement(By.xpath("//button[contains(text(),'dismiss')]")).click();
      driver.findElement(By.cssSelector(".Modal__ExitButton-sc-1go9jfn-2.kwZYRh")).click();
     driver.findElement(By.cssSelector(".Button-sc-9irl08-0.cases__DatePickerButton-sc-jdyzsi-16.fbIlgK")).click();
      driver.findElement(By.xpath("//button[contains(text(),'CLEAR')]")).click();
	   driver.findElement(By.xpath("//button[contains(text(),'DONE')]")).click();
	   driver.findElement(By.cssSelector(".Icon__IconContainer-sc-1tzeys2-0.eVXKZx.CaseFilterHeaderSearch__StyledIcon-sc-13iyp9h-2.jDPiIk")).click();
	   //Reading the case id from my file 
//      File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
//      FileReader FR = new FileReader(fi);
//      BufferedReader BR = new BufferedReader(FR);
//	   String caseid =BR.readLine();
	   driver.findElement(By.xpath("//label[contains(@class, 'mdc-text-field') and .//span[text()='Order Id']]//input[@class='mdc-text-field__input']")).sendKeys("WN675G2YT4",Keys.ENTER);
	   Thread.sleep(2000);
	   driver.findElement(By.id("table-body-view-cell")).click();
      String parentwindow =driver.getWindowHandle();
     System.out.println("IQC window id :"+parentwindow);
	   Set <String> handels =driver.getWindowHandles();
	    for (String handel : handels ) {
      if (!handel.equals(parentwindow)) {
     driver.switchTo().window(handel);
	  driver.findElement(By.xpath("//button[contains(text(),'Start Reporting')]")).click();
   
     //WebElement spanElementWithValue = driver.findElement(By.xpath("//span[contains(text(), 'Observations')]"));
	  
	  
	  try {
		  WebElement   ww= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div[3]/div[1]/div/div/div[2]/div[4]/span[2]/span/span/strong/span"));
		  ww.sendKeys("I have a cure for insomnia. It’s probably worth millions of dollars but I’m giving it to you free. It isn’t warm milk or chamomile tea. It’s list making");
	  }
	  catch(Exception obj) {
		  WebElement  ww= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div[3]/div[1]/div/div/div[2]/div[4]/span[2]/span/span/span"));
		  ww.sendKeys("I have a cure for insomnia. It’s probably worth millions of dollars but I’m giving it to you free. It isn’t warm milk or chamomile tea. It’s list making");
	  }
	  
	 
	  
	  
	  
      

    		  
      
    
//	   driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
//	   Thread.sleep(3000);
//	   driver.findElement(By.xpath("//button[contains(text(),'Submit & stop reporting')]")).click();
      }
   
	    }
	    }

		
		private static void setClipboardPermission(ChromeOptions options) {
      // Set up ChromeOptions for clipboard permission handling
      Map<String, Object> prefs = new HashMap<>();
      Map<String, Object> profile = new HashMap<>();
      Map<String, Object> contentSettings = new HashMap<>();

      // Configure clipboard permission
      prefs.put("profile.content_settings.exceptions.clipboard", getClipboardSettingsMap(1));
      profile.put("content_settings", contentSettings);
      prefs.put("profile", profile);
      options.setExperimentalOption("prefs", prefs);
      }

  private static Map<String, Object> getClipboardSettingsMap(int settingValue) {
      Map<String, Object> map = new HashMap<>();
      map.put("last_modified", String.valueOf(System.currentTimeMillis()));
      map.put("setting", settingValue);
      Map<String, Object> cbPreference = new HashMap<>();
      cbPreference.put("[*.],*", map);
      return cbPreference;
      }
}
