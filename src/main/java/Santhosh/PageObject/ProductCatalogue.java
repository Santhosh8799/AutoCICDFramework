package Santhosh.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santhosh.AbstractComponent.AbstractComponent;

//Page object is just to store locators and methods data needs to driven 
public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	//initially driver value will be null so we use constructor to get the driver from parent
	//constructor will execute first when object is created
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement viewcart;
	
	By locator=By.cssSelector(".mb-3");
	By invisible=By.cssSelector("div[id='toast-container']");
	By locator1=By.cssSelector("b");
	By addtocart=By.cssSelector("button:last-of-type");
	
    public void clickontProduct(String productname) {

    	waitForElementToAppear(locator);
    	for (WebElement product : products) {
    		//When we have product.findelement we cant create page Factroy instead we have to use By locator
    		//when we have driver.findElement then only we can create driver.findElement.
    	  WebElement productNameElement = product.findElement(locator1);
    	  String productNameText = productNameElement.getText();
    	  if (productNameText.equals(productname)) {
    	    WebElement addToCartButton = product.findElement(addtocart);
    	    addToCartButton.click();
    	    break; // Exit the loop after finding the desired product
    	  }
    	}
    	
    }
    
    public void viewCart() {
    	waitForElementtoDisappear(invisible);
    	viewcart.click();
    }
	

	
}
