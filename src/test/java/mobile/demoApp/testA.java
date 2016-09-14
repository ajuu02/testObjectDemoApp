package mobile.demoApp;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import utilities.DriverManager;

public class testA extends DriverManager{
  public testA() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
  @BeforeMethod
  public void startTest() throws IOException, InterruptedException
  {
	  setUpDevice();
	  System.out.println("App is Installed...");
  }  
  
  @Test
  public void dragdroptest() throws IOException, InterruptedException {
	 
	  	  System.out.println("Runing Test dragdroptest");
	  	  Thread.sleep(5000);
	  	  takeScreenShot();
	  	  driver.findElementByName("Basic usage playground").click();
	  	  takeScreenShot();
	  	  //Locate 3rd element from list to drag.
	  	  WebElement ele1 = (WebElement) driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle").get(2);
	  	  //Locate 6th element to drop dragged element.
	  	  WebElement ele2 = (WebElement) driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle").get(5);

	  	  //Perform drag and drop operation using TouchAction class.
	  	  //Created object of TouchAction class.
	  	  TouchAction action = new TouchAction((MobileDriver) driver);
	  	  
	  	  System.out.println("It Is dragging element.");
	  	  //It will hold tap on 3rd element and move to 6th position and then release tap.
	  	  action.longPress(ele1).moveTo(ele2).release().perform();  
	  	  System.out.println("Element has been droped at destination successfully.");
	  	  takeScreenShot();
	  	  
  }
  
  @Test
  public void homeMenutest() throws IOException, InterruptedException {
	 
	  	  System.out.println("Runing Test homeMenutest");
	  	  Thread.sleep(2000);
	  	  
	  	  driver.findElementByName("Basic usage playground").click();

	  	  //Locate 3rd element(Chick Corea) from list to drag.
	  	  for (int index=0;index<3;index++)
	  	  {
	  	  WebElement ele1 = (WebElement) driver.findElement(By.xpath("//android.widget.ImageButton"));
	  	  ele1.click();
	  	  
	  	  driver.findElement(By.xpath("//android.widget.LinearLayout[contains(@index,'"+index+"')]")).click();
	  	  Thread.sleep(2000);
	  	  takeScreenShot();
	  	  driver.findElement(By.xpath("//android.widget.Button[contains(@text,'Cancel')]")).click();;
	  	  Thread.sleep(2000);
	  	  //Locate 6th element to drop dragged element.
	  	  }
	  	  
  }
  
  @AfterMethod
  public void EndTest() throws IOException, InterruptedException
  {
	  uninstallApp();
	  System.out.println("App is Uninstalled..");
	  driver.quit();
 
  } 
}
