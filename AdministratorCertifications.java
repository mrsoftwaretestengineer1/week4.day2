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

public class AdministratorCertifications {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		
		//1. Launch Salesforce application https://login.salesforce.com/
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//2. Login with username as "ramkumar.ramaiah@testleaf.com " and password as "Password$123"
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
		
		//3. Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		//4. Click confirm on Confirm redirect
		String windowHandle=driver.getWindowHandle();
		
		Set<String>windowHandles=driver.getWindowHandles();
		System.out.println("Number of window handles is" +windowHandles.size());
		
		List<String>lstWindowHandles=new ArrayList<String>(windowHandles);
		String secondWindowHandle=lstWindowHandles.get(1);
		System.out.println(secondWindowHandle);
			
		driver.switchTo().window(secondWindowHandle);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//5. Click Resources and mouse hover on Learning On Trailhead
		Shadow dom=new Shadow(driver);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement learning=dom.findElementByXPath("//span[text()='Learning']");
		wait.until(ExpectedConditions.visibilityOf(learning));
		learning.click();
		WebElement learn=dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		wait.until(ExpectedConditions.visibilityOf(learn));
		
		Actions builder=new Actions(driver);
		builder.moveToElement(learn).perform();

		
		//6. Click on Salesforce Certifications
		WebElement certificate=dom.findElementByXPath("//a[text()='Salesforce Certification']");
		Actions builder1=new Actions(driver);
		builder1.moveToElement(certificate).perform();
		certificate.click();
		
		//6. Click on Ceritification Administrator
		driver.findElement(By.xpath("//a[text()='Administrator']")).click();
		
		//7. Navigate to Certification - Administrator Overview window
		String title=driver.getTitle();
		System.out.println("Title is" +title);
		
		//8. Verify the Certifications available for Administrator Certifications should be displayed
		String head=driver.findElement(By.xpath("//div[@class='certification-banner_title slds-container--medium slds-container--center slds-text-align--center']")).getText();
		System.out.println("Page heading is "+head);

	}

}