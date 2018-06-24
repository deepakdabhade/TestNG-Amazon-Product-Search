package productSearch;

import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.Logger;

public class SearchProduct {
	
	public WebDriver driver;
	private static Logger Log = Logger.getLogger(SearchProduct.class.getName());
     
         
  @Test
  public void AmazonProductSearch() {
	try
	{
	  DOMConfigurator.configure("log4j.xml");	 
	  
	  // launch the Chrome browser and open the application url
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
	  driver=new ChromeDriver();
	  
	  //maximize the browser window
	  driver.manage().window().maximize();
	  Log.info("Browser opened");
	
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
	  driver.get("https://www.amazon.com/");       
	  Log.info("Amazon site launched");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
	  //enter a product name in textbox
       WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
       search.sendKeys("Nikon");
       Log.info("Entered search text - 'Nikon'");
       
       //click on the Search button
       WebElement SearchButton = driver.findElement(By.className("nav-input"));
       SearchButton.click();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Log.info("Clicked on Search button");
       
       //click on sort dropdown
       WebElement sortdropdown = driver.findElement(By.id("sort"));
       sortdropdown.click();
       Log.info("Clicked on sort dropdown");
     
       //Select High to Low from dropdown
       WebElement SelectOption = driver.findElement(By.id("sort"));
       Select dropdown= new Select(SelectOption);
       dropdown.selectByIndex(3);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Log.info("Selected option 'High to Low'");
             
       //Click on second product
       WebElement productlist = driver.findElement(By.xpath("//*[@id='result_1']/div/div/div/div[2]/div[1]/div[1]/a/h2"));
       productlist.click();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Log.info("Selected second product");
       
       //Get product tile
       WebElement producttitle = driver.findElement(By.xpath("//*[@id='productTitle']"));
       String actualTitle = producttitle.getText();      
       String expectedTitle = "Nikon D3X";
             
       Assert.assertEquals(actualTitle,expectedTitle,"Product title not matching");  
       
	}
	catch (Exception e)
	{
		Log.error("Product title not matching", e);
		Assert.fail("Product title not matching");
	}
	       
  }
}


