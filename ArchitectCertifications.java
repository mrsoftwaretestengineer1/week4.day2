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

public class ArchitectCertifications {

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
		    
		   //4. Click  On Resources and mouse hover on Learning On Trailhead
		    String windowHandle=driver.getWindowHandle();
			
			Set<String>windowHandles=driver.getWindowHandles();
			System.out.println("Number of window handles is" +windowHandles.size());
			
			List<String>lstWindowHandles=new ArrayList<String>(windowHandles);
			String secondWindowHandle=lstWindowHandles.get(1);
			System.out.println(secondWindowHandle);
			driver.switchTo().window(secondWindowHandle);
			driver.findElement(By.xpath("//button[text()='Confirm']")).click();
			
			
			Shadow dom=new Shadow(driver);
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			WebElement learning=dom.findElementByXPath("//span[text()='Learning']");
			wait.until(ExpectedConditions.visibilityOf(learning));
			learning.click();
			WebElement trailhead=dom.findElementByXPath("//span[text()='Learning on Trailhead']");
			wait.until(ExpectedConditions.visibilityOf(trailhead));
			Actions builder=new Actions(driver);
			builder.moveToElement(trailhead).perform();
			
		   //5. Select SalesForce Certification Under Learnings
			WebElement certification=dom.findElementByXPath("//a[text()='Salesforce Certification']");
			Actions builder1=new Actions(driver);
			builder1.moveToElement(certification).perform();
			certification.click();
		    
	       //6. Choose Your Role as Salesforce Architect
			driver.findElement(By.xpath("(//div[text()='Salesforce'])[2]")).click();
		    
		   //7. Get the Text(Summary) of Salesforce Architect 
			String summary=driver.findElement(By.xpath("//h1[text()='Salesforce Architect']//following::div")).getText();
			System.out.println("Summary: " +summary);
		    
		   //8. Get the List of Salesforce Architect Certifications Available
			List<WebElement> certifications=driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
			System.out.println("List of Salesforce Architect Certifications Available : ");
			for(int i=0;i<certifications.size();i++)
			{
				String salesforceCertifications=certifications.get(i).getText();
				System.out.println(salesforceCertifications);
			}
		    
		   //9. Click on Application Architect
			driver.findElement(By.linkText("Application Architect")).click();
		    
		   //10.Get the List of Certifications available
			String display=driver.findElement(By.xpath("//div[@class='certification-banner_title slds-container--medium slds-container--center slds-text-align--center']")).getText();
			System.out.println("List of Certifications is "+display);

	}

}