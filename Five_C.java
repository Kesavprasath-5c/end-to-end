package fiveC_happy_flow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Five_C {

	@Test
	public void case_Activate() throws Exception {
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

	@Test(dependsOnMethods = { "case_Activate" })
	public void getcase_id() throws Exception {
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
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//span[contains(text(), 'Finish')]")).click();
		   } 
		catch (Exception ee) {
			driver.findElement(By.xpath("//span[contains(text(), 'Finish')]")).click();
			driver.findElement(By.xpath("//span[contains(text(), 'Next')]")).click();
			driver.findElement(By.xpath("//span[contains(text(), 'Next')]")).click();
			driver.findElement(By.xpath("//span[contains(text(), 'Finish')]")).click();
		}
		driver.findElement(By.xpath("//a[contains(text(),'Orders')]")).click();
		String id = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody/tr[2]/td[1]/div/div[2]")).getText();
		// Storing the case_id into my file
		File ff = new File("/Users/Kesav/Desktop/caseid/id.text");
		try {
			FileWriter FW = new FileWriter(ff);
			BufferedWriter BW = new BufferedWriter(FW);
			BW.write(id);
			BW.flush();
			BW.close();
			System.out.println("case id successfully written to the file.");
			System.out.println("Case id :" + id);
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

	@Test(dependsOnMethods = { "getcase_id" })
	public void assign_to_IQC() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://e2e-staging-admin.5cnetwork.com");
		driver.findElement(By.id("email")).sendKeys("demo@clincalops.com");
		driver.findElement(By.id("password")).sendKeys("RadKings@3", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@href,'/qc/coordinator')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".Icon__IconContainer-sc-1tzeys2-0.eVXKZx.CaseFilterHeaderSearch__StyledIcon-sc-10yf6wp-2.fjXPqE")).click();
        File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
		FileReader FR = new FileReader(fi);
		BufferedReader BR = new BufferedReader(FR);
		String caseid = BR.readLine();
		driver.findElement(By.xpath(
				"//label[contains(@class, 'mdc-text-field') and .//span[text()='Order Id']]//input[@class='mdc-text-field__input']")).sendKeys(caseid);
		driver.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
		driver.findElement(By.cssSelector(".coordinator__QCExecutiveNameContainer-sc-1jhci00-29.jplptO")).click();
		driver.findElement(By.id("search-ams-input")).sendKeys("demo");
		driver.findElement(By.xpath("//span[contains(text(),'Demo IQC')]")).click();
		Thread.sleep(2000);
		driver.close();
	}

	@Test(dependsOnMethods = { "assign_to_IQC" })
	public void iqc_reportable() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://e2e-staging-admin.5cnetwork.com/");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("demo@iqc.com");
		driver.findElement(By.id("password")).sendKeys("RadKings@3", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".Button-sc-9irl08-0.qc__DatePickerButton-sc-b06d1u-9.fbIlgK")).click();
		driver.findElement(By.xpath("//button[contains(text(),'CLEAR')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'DONE')]")).click();
		// Reading the case id from my file
		File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
		FileReader FR = new FileReader(fi);
		BufferedReader BR = new BufferedReader(FR);
		String caseid = BR.readLine();
		// below lines of code is used to open the case based on give caseid value
		WebElement spanElementWithValue = driver.findElement(By.xpath("//span[contains(text(), '" + caseid + "')]"));
		WebElement lastSpanElement = spanElementWithValue
				.findElement(By.xpath("./ancestor::div[contains(@class, 'qc__TableBodyRow')]/div[last()]/span"));
		lastSpanElement.click();
		String parentwindow = driver.getWindowHandle();
		System.out.println("IQC window id :" + parentwindow);
		Set<String> handels = driver.getWindowHandles();
		for (String handel : handels) {
			if (!handel.equals(parentwindow)) {
				driver.switchTo().window(handel);
				driver.findElement(By.id("toggle--Take break after this caselabel")).click();				
		    	driver.findElement(By.id("toggle--No")).click();
		    	driver.findElement(By.id("remarks")).sendKeys("Testing");
				driver.findElement(By.xpath("//button[contains(@data-cy,'reportable-button')]")).click();
				try {
				driver.findElement(By.xpath("//button[contains(text(),'Take Break')]")).click();
				}
				catch(Exception er) {
					
				}
				Thread.sleep(7000);
				driver.findElement(By.xpath("//div[contains(text(),'mark case as reportable ')]")).click();
			}
		}
		Thread.sleep(2000);
		driver.close();
	}

	@Test(dependsOnMethods = { "iqc_reportable" })

	public void assign_to_rad() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://e2e-staging-admin.5cnetwork.com");
		driver.findElement(By.id("email")).sendKeys("demo@clincalops.com");
		driver.findElement(By.id("password")).sendKeys("RadKings@3", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@href,'/cases')]")).click();
		driver.findElement(By.cssSelector(".Icon__IconContainer-sc-1tzeys2-0.eVXKZx.CaseFilterHeaderSearch__StyledIcon-sc-10yf6wp-2.fjXPqE")).click();
		File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
		FileReader FR = new FileReader(fi);
		BufferedReader BR = new BufferedReader(FR);
		String caseid = BR.readLine();
		driver.findElement(By.xpath("//label[contains(@class,'mdc-text-field') and .//span[text()='Order Id']]//input[@class='mdc-text-field__input']")).sendKeys(caseid);
		driver.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
        String[] buttonList = { "toggle--Non Reportable", "toggle--IQC Cases", "toggle--OQC Cases", "toggle--Reworks",
				"toggle--Completed" };
		driver.findElement(By.id("toggle--In Radiologist Poollabel")).click();
		for (String i : buttonList) {
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
		Thread.sleep(2000);
		driver.close();
	}

	@Test(dependsOnMethods = { "assign_to_rad" })
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
		driver.findElement(By.id("password")).sendKeys("demo123", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'dismiss')]")).click();
		driver.findElement(By.cssSelector(".Modal__ExitButton-sc-1go9jfn-2.kwZYRh")).click();
		driver.findElement(By.cssSelector(".Button-sc-9irl08-0.cases__DatePickerButton-sc-jdyzsi-16.fbIlgK")).click();
		driver.findElement(By.xpath("//button[contains(text(),'CLEAR')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'DONE')]")).click();
		driver.findElement(By.cssSelector(".Icon__IconContainer-sc-1tzeys2-0.eVXKZx.CaseFilterHeaderSearch__StyledIcon-sc-13iyp9h-2.jDPiIk")).click();
		// Reading the case id from my file
		File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
		FileReader FR = new FileReader(fi);
		BufferedReader BR = new BufferedReader(FR);
		String caseid = BR.readLine();
		driver.findElement(By.xpath(
			"//label[contains(@class, 'mdc-text-field') and .//span[text()='Order Id']]//input[@class='mdc-text-field__input']")).sendKeys(caseid, Keys.ENTER);
		driver.findElement(By.id("table-body-view-cell")).click();
		String parentwindow = driver.getWindowHandle();
		System.out.println("IQC window id :" + parentwindow);
		Set<String> handels = driver.getWindowHandles();
		for (String handel : handels) {
			if (!handel.equals(parentwindow)) {
				driver.switchTo().window(handel);
				driver.findElement(By.xpath("//button[contains(text(),'Start Reporting')]")).click();
				try {
					WebElement ww = driver.findElement(By.xpath(
							"/html/body/div[1]/div[2]/div/div[3]/div[3]/div[1]/div/div/div[2]/div[4]/span[2]/span/span/strong/span"));
					ww.sendKeys(
							"I have a cure for insomnia. It’s probably worth millions of dollars but I’m giving it to you free. It isn’t warm milk or chamomile tea. It’s list making");
				} 
				catch (Exception obj) {
                 WebElement ww = driver.findElement(By.xpath(
							"/html/body/div[1]/div[2]/div/div[3]/div[3]/div[1]/div/div/div[2]/div[4]/span[2]/span/span/span"));
					ww.sendKeys(
							"I have a cure for insomnia. It’s probably worth millions of dollars but I’m giving it to you free. It isn’t warm milk or chamomile tea. It’s list making");
					}
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(),'Submit & stop reporting')]")).click();
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

	@Test(dependsOnMethods = { "reportable" })
	public void assign_to_OQC() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://e2e-staging-admin.5cnetwork.com");
		driver.findElement(By.id("email")).sendKeys("demo@clincalops.com");
		driver.findElement(By.id("password")).sendKeys("RadKings@3", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@href,'/qc/coordinator')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".Icon__IconContainer-sc-1tzeys2-0.eVXKZx.CaseFilterHeaderSearch__StyledIcon-sc-10yf6wp-2.fjXPqE")).click();
        File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
		FileReader FR = new FileReader(fi);
		BufferedReader BR = new BufferedReader(FR);
		String caseid = BR.readLine();
		driver.findElement(By.xpath("//label[contains(@class, 'mdc-text-field') and .//span[text()='Order Id']]//input[@class='mdc-text-field__input']")).sendKeys(caseid);
		driver.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
		driver.findElement(By.cssSelector(".coordinator__QCExecutiveNameContainer-sc-1jhci00-29.jplptO")).click();
		driver.findElement(By.id("search-ams-input")).sendKeys("demo");
		driver.findElement(By.xpath("//span[contains(text(),'Demo IQC')]")).click();
		Thread.sleep(2000);
		driver.close();
	}

	@Test(dependsOnMethods = { "assign_to_OQC" })
	public void oqc_approvel() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/Kesav/Downloads/chromedriver-mac-x64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://e2e-staging-admin.5cnetwork.com/");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("demo@iqc.com");
		driver.findElement(By.id("password")).sendKeys("RadKings@3", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".Button-sc-9irl08-0.qc__DatePickerButton-sc-b06d1u-9.fbIlgK")).click();
		driver.findElement(By.xpath("//button[contains(text(),'CLEAR')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'DONE')]")).click();
    	File fi = new File("/Users/Kesav/Desktop/caseid/id.text");
		FileReader FR = new FileReader(fi);
		BufferedReader BR = new BufferedReader(FR);
		String caseid = BR.readLine();
		// below lines of code is used to open the case based on give caseid value
		WebElement spanElementWithValue = driver.findElement(By.xpath("//span[contains(text(), '" + caseid + "')]"));
		WebElement lastSpanElement = spanElementWithValue.findElement(By.xpath("./ancestor::div[contains(@class, 'qc__TableBodyRow')]/div[last()]/span"));
		lastSpanElement.click();
		String parentwindow = driver.getWindowHandle();
		System.out.println("IQC window id :" + parentwindow);
		Set<String> handels = driver.getWindowHandles();
		for (String handel : handels) {
			if (!handel.equals(parentwindow)) {
				driver.switchTo().window(handel);
				// if we have Correlation then uncommend below;
				try {
				driver.findElement(By.id("toggle--Yes")).click();
				}
				catch(Exception ee) {
					System.out.println("QA correlation is not found");
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