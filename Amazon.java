package december17;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		Actions builder = new Actions(driver);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
		String text = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println(text);
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-star-small a-star-small-4 aok-align-bottom'])[1]")).click();
		String stars = driver.findElement(By.xpath("(//span[@class='a-size-base']/a)[1]")).getText();
		System.out.println(stars);
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		 Set<String> win = driver.getWindowHandles();
		    List<String>Mobile = new ArrayList<String>(win);
		    driver.switchTo().window(Mobile.get(1));
		  File screenshotAs = driver.getScreenshotAs(OutputType.FILE);//method used to take Screenshot
		    File target = new File("./report/img.png");	//set the storage path
		    FileUtils.copyFile(screenshotAs, target);//link the source and target files
		    System.out.println("CLICKED SNAPSHOT");
		    driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		    String cart = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-total-string']")).getText();
		    System.out.println(cart);
		    if(text.equalsIgnoreCase(cart)) {
		    	System.out.println("same");
		    }else {
		    	System.out.println("differentr");
		    	driver.quit();
		    }
	}

}
