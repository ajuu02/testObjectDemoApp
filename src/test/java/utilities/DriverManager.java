package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class DriverManager extends deviceManager{
	public Properties objprop;
	public static AndroidDriver driver;
	Dimension size;
	String destDir;
	DateFormat dateFormat;
	public DriverManager() throws IOException {
		super();
		
	}


	DesiredCapabilities capabilities;
	
	

	public void setUpDevice() throws IOException, InterruptedException
	{
		FileInputStream obs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Properties\\object.properties");
		objprop = new Properties();
		objprop.load(obs);
		
		launchApp();
		capabilities = new DesiredCapabilities();

	  // Set android deviceName desired capability. Set your device name.
	  capabilities.setCapability("deviceName", configprop.getProperty("deviceName"));

	  // Set BROWSER_NAME desired capability. It's Android in our case here.
	  capabilities.setCapability(CapabilityType.BROWSER_NAME, configprop.getProperty("browserName"));

	  // Set android VERSION desired capability. Set your mobile device's OS version.
	  capabilities.setCapability(CapabilityType.VERSION, configprop.getProperty("osVersion"));

	  // Set android platformName desired capability. It's Android in our case here.
	  capabilities.setCapability("platformName", configprop.getProperty("platform"));

	  // Set android appPackage desired capability. It is
	 // capabilities.setCapability("appPackage", "de.dbsystel.rim.base");
	  capabilities.setCapability("appPackage", configprop.getProperty("appPackage_demoApp"));

	  // Set android appActivity desired capability. It is
	  //capabilities.setCapability("appActivity", "de.dbsystel.rim.base.activities.HomeScreen");
	  capabilities.setCapability("appActivity", configprop.getProperty("appactivity_demoApp"));
	  
	  //TestObject Capabilities
	  capabilities.setCapability("testobject_api_key", configprop.getProperty("testobject_api_key_val"));
	  capabilities.setCapability("testobject_app_id", configprop.getProperty("testobject_app_id_val"));
	  capabilities.setCapability("testobject_device", configprop.getProperty("testobject_device_val"));
	  
	  try {
		//driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		//testObject
		driver = new AndroidDriver(new URL(configprop.getProperty("TestObjectUrl")), capabilities);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);

	}
	
	public void takeScreenShot() {
		  // Set folder name to store screenshots.
		  String destDir = "screenshots";
		  // Capture screenshot.
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  // Set date format to set It as screenshot file name.
		  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // Create folder under project with name "screenshots" provided to destDir.
		  new File(destDir).mkdirs();
		  // Set file name using current date time.
		  String destFile = dateFormat.format(new Date()) + ".png";

		  try {
		   // Copy paste file at destination folder location
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }
	
	
}
