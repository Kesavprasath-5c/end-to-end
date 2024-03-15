package fiveC_happy_flow;
import java.util.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Pre_Read {
    @Test
	public void case_activation() throws Exception {
    	
//    	Pre_Read test = new Pre_Read();
    	
  // 	HashMap<String, String> map = new HashMap<>();
//  	map.put("study_name", "ct brain");
//    	map.put("url", "https://e2e-staging-client.5cnetwork.com");
//    	
//    	
//    	
//    	HashMap<String, String> map2 = new HashMap<>();
//    	map.put("study_name", "mri brain");
//    	map.put("url", "https://e2e-sandbox-client.5cnetwork.com");
    	
    	
    
    	
    	// Step 1: activate ct brain case --->
    	
    	Pre_Read.activateCase("ct brain", "https://e2e-qa-client.5cnetwork.com", "jeevan.hospital@mailinator.com", "Jeevan@123");
    
	
    }
    
    
    public static void activateCase(String modlaityName, String env, String email, String pass ) throws Exception {
    	ChromeOptions opt = new ChromeOptions();
	   opt.addArguments("disable-notifications");
	   setNotificationPermission(opt);
	    System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
	    
	    WebDriver driver = new ChromeDriver(opt);
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.get(env);
	    driver.manage().window().maximize();
	    driver.findElement(By.id("basic_emailId")).sendKeys(email);
	    driver.findElement(By.id("basic_password")).sendKeys(pass,Keys.ENTER);
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector(".anticon.anticon-close.ant-tour-close")).click();
	    driver.findElement(By.cssSelector("a[href='/drafts']")).click();
	    driver.findElement(By.cssSelector(".ant-btn.css-dev-only-do-not-override-59t0xy.ant-btn-primary.ant-btn-dangerous.sc-fFDWmC.iJBkYl")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div/div/div[1]/div[2]/div[2]/div/div[2]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody/tr[2]/td[8]/div/div/div[2]")).click();
	    driver.findElement(By.cssSelector(".anticon.anticon-close.ant-tour-close")).click();
	    Thread.sleep(2000);
		driver.findElement(By.cssSelector(".anticon.anticon-close.ant-modal-close-icon")).click();
		driver.findElement(By.id("nest-messages_user_TreferringPhysician")).sendKeys("ken");
	    driver.findElement(By.id("nest-messages_user_Tage")).sendKeys("27");
		driver.findElement(By.xpath("//*[@id=\"nest-messages_user_patientType\"]/label[1]/span[2]")).click();
		driver.findElement(By.cssSelector(".ant-select.ant-select-in-form-item.css-dev-only-do-not-override-59t0xy.ant-select-multiple.ant-select-allow-clear.ant-select-show-arrow.ant-select-show-search")).click();
		//driver.findElement(By.cssSelector("div[title='CT Brain']")).click();
		List<WebElement> ee = driver.findElements(By.cssSelector(".ant-select-selection-search-input"));
		WebElement elem = ee.get(1);
		elem.sendKeys(modlaityName);
		elem.sendKeys(Keys.ENTER);
		elem.sendKeys("CT pns");
		elem.sendKeys(Keys.ENTER);
    }

	
	
	

    private static void setNotificationPermission(ChromeOptions options) {
		// Set up ChromeOptions for clipboard permission handling
		Map<String, Object> prefs = new HashMap<>();
		Map<String, Object> profile = new HashMap<>();
		Map<String, Object> contentSettings = new HashMap<>();
       // Configure clipboard permission
		prefs.put("profile.content_settings.exceptions.notifications", getNotificationSettingsMap(1));
	profile.put("content_settings", contentSettings);
		prefs.put("profile", profile);
		options.setExperimentalOption("prefs", prefs);
		       }

	private static Map<String, Object> getNotificationSettingsMap(int settingValue) {
	Map<String, Object> map = new HashMap<>();
		map.put("last_modified", String.valueOf(System.currentTimeMillis()));
		map.put("setting", settingValue);
		Map<String, Object> cbPreference = new HashMap<>();
		cbPreference.put("[*.],*", map);
		return cbPreference;
		       }
}

