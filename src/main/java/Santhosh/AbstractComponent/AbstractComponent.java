package Santhosh.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;

	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void waitForElementToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementtoDisappear(By invisible) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(invisible));
	}
	
	public void mouseOver(WebElement over,String selection) {
		Actions a=new Actions(driver);
		a.sendKeys(over, selection).build().perform();
	}
	
	public void waitForWebElementToAppear(WebElement appear) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(appear));
	}
	

}
