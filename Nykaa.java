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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		
		    //1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
			//2) Mouse over on brands
		WebElement brands=driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(brands).perform();
        

			//3) Click L'Oreal Paris
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		
			//4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title=driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("Title contains L'Oreal Paris");
		}else {
			System.out.println("Title does not contain L'Oreal Paris");
		}
			
			//5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']")).click();
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']/following::ul/div[4]/label/div[2]")).click();
		
			//6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='30']/following::div")).click();
		
			//7) Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']/following::div")).click();
		
			//8)check whether the Filter is applied with Shampoo
		
			//9) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[contains(text(),'Paris Colour Protect Shampoo')]")).click();
		
			//10) GO to the new window and select size as 175ml
		String windowHandle=driver.getWindowHandle();
		System.out.println(windowHandle);
		  
		Set<String>windowHandles=driver.getWindowHandles();
		System.out.println("How many handles?" +windowHandles.size());
			
		List<String>lstWindowHandles=new ArrayList<String>(windowHandles);	
		String secondWindowHandle=lstWindowHandles.get(1);
		System.out.println(secondWindowHandle);
			
		driver.switchTo().window(secondWindowHandle);
		
		WebElement dropdown=driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select select=new Select(dropdown);
		select.selectByVisibleText("175ml");
		
			//11) Print the MRP of the product
		String mrp=driver.findElement(By.xpath("//span[text()='MRP:']//following-sibling::span']")).getText();
		System.out.println("MRP of the product is "+mrp);
		
			//12) Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		
		//13) Go to Shopping Bag/
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement bag=driver.findElement(By.xpath("//button[@class='css-g4vs13']"));
		wait.until(ExpectedConditions.visibilityOf(bag));
		bag.click();
		
	    //14) Print the Grand Total amount/
		driver.switchTo().frame(0);
		WebElement total=driver.findElement(By.xpath("(//span[text()='Grand Total']/following::div)[1]"));
		String amtText=total.getText();
		int grandTotal=Integer.parseInt(amtText);
		System.out.println("The grand total is "+grandTotal);
		
			//15) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
			//16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		
			//17) Check if this grand total is the same in step 14
		String checkoutsum=driver.findElement(By.xpath("(//div[@class='value']/span)[2]")).getText();
		int grandSum=Integer.parseInt(checkoutsum);
		if(grandTotal==grandSum)
		{
			System.out.println("Grand Total and Grand Sum are equal");
		}else
		{
			System.out.println("Grand Total is "+grandTotal+" and Grand Sum is "+grandSum);
		}
		
			//18) Close all windows
		driver.quit();

	}

}