package Santhosh.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAlonetest {
	
	//Tmmrw Try amazon Application 
	public static void main(String[] args) {
	
    WebDriver driver;
    String productname="QWERTY";
    WebDriverWait wait;
    // Set the path to the ChromeDriver executable
	System.setProperty("webdriver.chromedriver.driver","C://Users//santh//Downloads//chromedriver-win64//chromedriver.exe");
	
	// Create an instance of ChromeDriver
	driver=new ChromeDriver();
	driver.get("https://rahulshettyacademy.com/client/");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	
	WebElement username=driver.findElement(By.xpath("//input[@type='email']"));
	username.sendKeys("santhosh42@gmail.com");
	WebElement password=driver.findElement(By.id("userPassword"));
	password.sendKeys("Sanrahul324@#");
	WebElement login=driver.findElement(By.name("login"));
	login.click();
	wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	for (WebElement product : products) {
	  WebElement productNameElement = product.findElement(By.cssSelector("b"));
	  String productNameText = productNameElement.getText();
	  if (productNameText.equals(productname)) {
	    WebElement addToCartButton = product.findElement(By.cssSelector("button:last-of-type"));
	    addToCartButton.click();
	    break; // Exit the loop after finding the desired product
	  }
	}
	
	//it will wait until the element to disappear from the webpage
	wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='toast-container']")));
	
	WebElement viewcart=driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
	viewcart.click();
	WebElement checkout=driver.findElement(By.xpath("//button[text()='Checkout']"));
	checkout.click();
	
	Actions a=new Actions(driver);
	a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    WebElement selectcountry=driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
    selectcountry.click();
	
	WebElement placeOrder=driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"));
	placeOrder.click();
	
    String confirmation=driver.findElement(By.cssSelector(".hero-primary")).getText();
    System.out.println(confirmation);
    Assert.assertEquals(confirmation, "THANKYOU FOR THE ORDER.");
    driver.close();
}
}
