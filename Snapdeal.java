package december17;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.snapdeal.com/");
		Actions builder = new Actions(driver);
		//mousehover on the mens fashion
		WebElement Men = driver.findElement(By.linkText("Men's Fashion"));
		builder.moveToElement(Men).perform();
		//click on the sports shoes
	    driver.findElement(By.linkText("Sports Shoes")).click();
	    String text = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
	    String replaceAll = text.replaceAll("\\D","");
	    int parseInt = Integer.parseInt(replaceAll);
	    System.out.println("SPORTS SHOES:"+parseInt);
	    //click on the training shoes
	    driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
	    //select sort by low to high
	    driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
	    WebElement low = driver.findElement(By.xpath("(//li[@data-index='1'])[2]"));
	    builder.moveToElement(low).click().perform();
	    Thread.sleep(6000);
	    //check the sorting of low to high 
	    List<WebElement> Price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
	    List<String> shoe = new ArrayList<String>();
	    for(int i=0;i<Price.size();i++) {
	    	String text2 = Price.get(i).getText();
	    	System.out.println(text2);
	    	shoe.add(text2);
	    	String replaceAll2 = text2.replaceAll("\\D", "");
	    	int parseInt2 = Integer.parseInt(replaceAll2);
	    	System.out.println(parseInt2);
	    }
	    //set the price as between 900 to 1200 and click the GO buttton
	    WebElement rate = driver.findElement(By.xpath("//input[@name='fromVal']"));
	    rate.clear();
	    rate.sendKeys("900");
	    WebElement torate = driver.findElement(By.xpath("//input[@name='toVal']"));
	    torate.clear();
	    torate.sendKeys("1200");
	    driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
	    Thread.sleep(6000);
	    //click view more button
	    driver.findElement(By.xpath("(//button[@class='view-more-button btn btn-line btn-theme-secondary viewMoreFilter' and text()='View More '])[1]")).click();
	    //click the navy filter
	    driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label")).click();
	    //check the all filters are applied
	    String Ptag = driver.findElement(By.xpath("(//a[@class='clear-filter-pill'])[1]")).getText();
	    String Ctag = driver.findElement(By.xpath("(//a[@class='clear-filter-pill'])[2]")).getText();
	    if(Ctag.contains("navy")) {
	    	System.out.println("NAVY COLOUR");
	    }else {
	    	System.out.println("OTHER COLOUR");
	    }if(Ptag.contains("900")) {
	    	System.out.println("IT IS IN REQUIRED PRICE");
	    }else {
	    	System.out.println("OTHER THAN A REQUIRED PRICE");
	    }
	    //mousehover on the first element
	    WebElement firstshoe = driver.findElement(By.xpath("(//p[@class='product-title'])[1]"));
	    builder.moveToElement(firstshoe).perform();
	    //click on the quick view button
	    driver.findElement(By.xpath("(//div[@class='clearfix row-disc']/div)[1]")).click();
	    Thread.sleep(5000);
	    //print the price and offer
	    String rupees = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
	    System.out.println(rupees);
	    String offer = driver.findElement(By.xpath("(//span[text()='82% Off'])[1]")).getText();
	    System.out.println(offer);
	    //taking screenshot
	    File screenshotAs = driver.getScreenshotAs(OutputType.FILE);//method used to take Screenshot
	    File target = new File("./report/img.png");	//set the storage path
	    FileUtils.copyFile(screenshotAs, target);//link the source and target files
	    System.out.println("CLICKED SNAPSHOT");
	    //close the current window
	    driver.close();
	    //close the all window
	    driver.quit();
	    
	
	
		
	}

}
