package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Customer_Service_Options {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		
		   //1. Launch Salesforce application https://login.salesforce.com/
			driver.get("https://login.salesforce.com/");
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		   //2. Login with Provided Credentials
			driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		    driver.findElement(By.id("password")).sendKeys("Password$123");
		    driver.findElement(By.id("Login")).click();
		    
		   //3. Click on Learn More link in Mobile Publisher
		    driver.findElement(By.xpath("//span[text()='Learn More']")).click();
            String windowHandle=driver.getWindowHandle();		
			Set<String>windowHandles=driver.getWindowHandles();			
			List<String>lstWindowHandles=new ArrayList<String>(windowHandles);
			String secondWindowHandle=lstWindowHandles.get(1);
			driver.switchTo().window(secondWindowHandle);
			driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		    
		   //4. Click on Products and Mousehover on Service
			Shadow dom=new Shadow(driver);
			WebElement products=dom.findElementByXPath("//span[text()='Products']");
			products.click();
			WebElement service=dom.findElementByXPath("//span[text()='Service']");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(service));
			Actions builder=new Actions(driver);
			builder.moveToElement(service).perform();
			
		   //5. Click on Customer Services
		   //6. Get the names Of Services Available 
			List<WebElement> servicesNames=dom.findElementsByXPath("//ul[@class='c360-panel-linkedlist__listitems']/li/h4/a[contains(text(),'Service')]");
			System.out.println("Services Available : ");
			for(int i=0;i<servicesNames.size();i++)
			{
				String name=servicesNames.get(i).getText();
				System.out.println(name);
			}
		   //7. Verify the title
			String title=driver.getTitle();
			System.out.println("Title is "+title);

	}

}