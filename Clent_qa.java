package fiveC_happy_flow;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Clent_qa {
	
@Test
	public static void case_Activate() throws Exception {
	ChromeOptions opt = new ChromeOptions();
	opt.addArguments("disable-notifications");
	setNotificationPermission(opt);
	System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
	WebDriver driver = new ChromeDriver(opt);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://e2e-staging-client.5cnetwork.com");
	driver.manage().window().maximize();
	driver.findElement(By.id("basic_emailId")).sendKeys("jeevan.hospital@mailinator.com");
	driver.findElement(By.id("basic_password")).sendKeys("Jeevan@123", Keys.ENTER);
	Thread.sleep(3000);
	driver.findElement(By.xpath("//span[contains(text(), 'Finish')]")).click();
	driver.findElement(By.xpath("//a[contains(text(), 'Draft')]")).click();
	driver.findElement(By.cssSelector(".ant-modal-close")).click();
	driver.findElement(By.xpath("//button[contains(text(),'Clear All')]")).click();
	driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody/tr[2]/td[8]/div/div/div[2]")).click();
	driver.findElement(By.xpath("//span[contains(text(), 'Next')]")).click();
	driver.findElement(By.xpath("//span[contains(text(), 'Next')]")).click();
	driver.findElement(By.xpath("//span[contains(text(), 'Finish')]")).click();
	driver.findElement(By.xpath("//button[@class='ant-modal-close']//span[contains(@class, 'ant-modal-close')]")).click();
	driver.findElement(By.id("nest-messages_user_Tage")).sendKeys("27");
	driver.findElement(By.id("nest-messages_user_TreferringPhysician")).sendKeys("ken");
	driver.findElement(By.xpath("//span[contains(text(), 'In Patient')]")).click();
	List<WebElement> ee = driver.findElements(By.cssSelector(".ant-select-selection-search-input"));
	WebElement elem = ee.get(1);
	elem.sendKeys("CT 3D");
	elem.sendKeys(Keys.ENTER);
	driver.findElement(By.id("nest-messages_user_clinical")).sendKeys("testing");
	driver.findElement(By.xpath("//button[contains(text(), 'Send for Reporting')]")).click();
	Thread.sleep(3000);
	driver.close();
        }
	
	
 @Test 
	public static void getcase_id() throws Exception {
	    ChromeOptions opt = new ChromeOptions();
		opt.addArguments("disable-notifications");
		setNotificationPermission(opt);
		System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
	    WebDriver driver = new ChromeDriver(opt);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://e2e-staging-client.5cnetwork.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("basic_emailId")).sendKeys("jeevan.hospital@mailinator.com");
		driver.findElement(By.id("basic_password")).sendKeys("Jeevan@123",Keys.ENTER);
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//span[contains(text(), 'Finish')]")).click();
		}
		catch(Exception ee) {
		driver.findElement(By.xpath("//span[contains(text(), 'Finish')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Next')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Next')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Finish')]")).click();
		}
	    driver.findElement(By.xpath("//a[contains(text(),'Orders')]")).click();
		String id =driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody/tr[2]/td[1]/div/div[2]")).getText();
		//Storing the case_id into my file 
		File ff = new File("/Users/Kesav/Desktop/caseid/id.text");
		  try {
			   FileWriter FW = new FileWriter(ff);
			   BufferedWriter BW = new BufferedWriter(FW);
			   BW.write(id);
			   BW.flush();
			   BW.close();
			   System.out.println("case id successfully written to the file.");
			   System.out.println("Case id :"+id);
		       } 
		  catch (Exception e) {
			   System.out.println("specified location is not exist");
		       }  
		   driver.close();  
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
	
	public static void main(String args[]) throws Exception {
		case_Activate();
		getcase_id();
		Admin admin_obj = new Admin();
		admin_obj.assign_to_IQC();
	}
		     
}


