package Santhosh.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

	WebDriver driver;
	@FindBy(css="table tbody td:nth-child(3)")
	List<WebElement> orderdetails;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement order;
	
	public OrderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean getOrderDetails(String productname) {
		for(WebElement detail:orderdetails)
		{
			if(detail.getText().equalsIgnoreCase(productname))
			{
				return true;
			}
		}
		return false;
	}
	
	public void goTOOrders() {
		order.click();
	}
}
