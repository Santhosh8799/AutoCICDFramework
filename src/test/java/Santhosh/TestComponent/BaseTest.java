package Santhosh.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Santhosh.PageObject.LoginPage;

public class BaseTest {

	public WebDriver driver;
	//Here we can initialize the driver to launch in different browser and use extends keyword in Test Class to inherit the methods and variables(inheritance Concept)
	//Creating Global Properties to decide the browser at run time
	
	public WebDriver intializeDriver() throws IOException {
		
	Properties prop=new Properties();
	//using prop object we can Extract the data using methods
	FileInputStream inputstream=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Santhosh//Resource//Global.properties");
	prop.load(inputstream);
	String browser=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("Browser");
	System.out.println(browser);
	
	//When I gave contains it worked earlier I provided equalsIgnorecase it didn't work
	if(browser.contains("chrome")) {
	ChromeOptions option=new ChromeOptions();
	if(browser.contains("headless")) {
	option.addArguments("headless");
	System.setProperty("webdriver.chromedriver.driver","C://Users//santh//Downloads//chromedriver-win64//chromedriver.exe");
		}
	// Create an instance of ChromeDriver
	driver=new ChromeDriver(option);
	}
	else if(browser.contains("edge"))
	{
		System.setProperty("webdriver.edge.driver","D://Drivers//edgedriver_win64//msedgedriver.exe");
		driver=new EdgeDriver();
	}
	else if(browser.contains("firefox"))
	{
		System.setProperty("webdriver.geckodriver.driver","D://Drivers//geckodriver-v0.35.0-win-aarch64//geckodriver.exe");
		driver=new FirefoxDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	return driver;
}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		//read json and converts to  string
	String jsonContent = 	FileUtils.readFileToString(new File(filepath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Datbind
	
	  ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}
	
	
	}
	
	public String getScreenshot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot take=(TakesScreenshot)driver;
		File src=take.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//Screenshots//"+testcasename+".png"));		
		return System.getProperty("user.dir")+"//Screenshots"+testcasename+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public void launch() throws IOException {
		driver=intializeDriver();
	    driver.get("https://rahulshettyacademy.com/client/");
	
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
	}
}