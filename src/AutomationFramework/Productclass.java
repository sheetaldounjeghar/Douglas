package AutomationFramework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;


public class Productclass {
	public String baseUrl = "https://www.douglas.de/";
    String driverPath = ".\\chromedriver.exe";
    public static WebDriver driver;
    static Float priceTxt;
    static int beautyPoint;
    @BeforeTest
    public void beforeTest() {

    	System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
  	  driver.manage().window().maximize();
  	  driver.get(baseUrl);
  	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
   @Test
    public static void startTest(){
    	
	   searchElement();
	   searchImage();
	   searchPrice();
	   searchCartBtn();
	   searchReserveBtn();
	   searchbeautypoints();
	   emaillink();
	   functionality();
}
     
     public static void searchElement(){
    	
    	 driver.findElement(By.xpath("/html/body/responsive-design/div[1]/div/div/div/div[2]/button")).click();
	    driver.findElement(By.name("query")).sendKeys("987345");//956114 987350 987345 042912 019002
		WebElement textbox = driver.findElement(By.name("query"));
		textbox.sendKeys(Keys.ENTER);
}
     
    public static void searchImage() {
    	
  	
    	WebElement imagesrc = null;
    	try
  	  {
    		imagesrc= driver.findElement(By.xpath("/html/body/responsive-design/main/div[1]/div/div/div[4]/div[1]/div/div/div[1]/div[2]/div/div/span[1]/div/div/div/img")) ;
  	  }
    	catch (NoSuchElementException e) {
    		 imagesrc= driver.findElement(By.xpath("/html/body/responsive-design/main/div[1]/div/div/div[4]/div[1]/div/div/div/div/img")) ;
    }
  	String src = ((JavascriptExecutor)driver).executeScript("return arguments[0].attributes['src'].value;", imagesrc).toString();
  	
  	  
  		if (!(src.contains(".jpg")||(src.contains(".png"))))
        {
             System.out.println("Image isn't Found, Hence Testcase Failed");
             
        }
        else
        {   
            System.out.println("Image is Found, Testcase Passed");
            
        }
  	  
    }
     
    public static void searchPrice() {	
    	
        WebElement price = driver.findElement(By.xpath("\r\n" + 
        		"/html/body/responsive-design/main/div[1]/div/div/div[4]/div[3]/div/div[1]/div/div[3]/div/span"));
		Boolean PricePresent =price.getText().contains("€") ;
        if (!PricePresent)
        {
             System.out.println("Price isn't Found, Hence Testcase Failed");
        }
        else
        {
    
            String temp = price.getText().substring(2).replace(',','.');
            priceTxt = Float.valueOf(temp);
    		
        }
  }
     
    public static void searchCartBtn() {
    	
        WebElement cart=driver.findElement(By.xpath("/html/body/responsive-design/main/div[1]/div/div/div[4]/div[3]/div/div[3]/button[1]"));
        Boolean cartpresent =cart.getText().contains("IN DEN WARENKORB") ;
        if (!cartpresent)
        {
             System.out.println("In den Warenkorb isn't found, Hence Testcase Failed");
        }
        else
        {
            System.out.println("In den Warenkorb is Found, Testcase Passed");
        }
  }
    
    public static void searchReserveBtn() {
    	
        WebElement reserve=driver.findElement(By.xpath("/html/body/responsive-design/main/div[1]/div/div/div[4]/div[3]/div/div[3]/button[2]"));
        Boolean reservepresent =reserve.getText().contains("IN IHRER FILIALE RESERVIEREN") ;
        if (!reservepresent)
        {
             System.out.println("In Ihrer Filiale reservieren isn't Found, Hence Testcase Failed");
        }
        else
        {
            System.out.println("In Ihrer Filiale reservieren is Found, Testcase Passed");
        }
  }
    
    public static void searchbeautypoints()
    {
    	
    WebElement points = driver.findElement(By.xpath("/html/body/responsive-design/main/div[1]/div/div/div[3]/div[1]"));
    String temp = points.getText().replaceAll("\\D", "");
	Boolean PointsPresent =points.getText().contains("+") ;
    if (!PointsPresent)
    {
         System.out.println("BeautyPoint isn't Found, Hence Testcase Failed");
    }
    else
    {
        System.out.println("BeautyPoint is Found, Testcase Passed"); 
        beautyPoint = Integer.parseInt(temp);
        
    	
        calbeautypoints(temp);
    }
    }
     
    public static void emaillink()
    {
    	
    	try {
        	WebElement email=driver.findElement(By.xpath("//*[contains(text(), 'Per E-Mail weiterempfehlen')]"));
            Boolean emailpresent =email.getText().contains("Per E-Mail weiterempfehlen");
           
        	if(!emailpresent)
           System.out.println("Per E-Mail weiterempfehlen is Found, Testcase passed");
            
        } catch (NoSuchElementException e) {
        	System.out.println("Per E-Mail weiterempfehlen isn't Found, Hence Testcase Failed");
        }
    }
    
    public static void calbeautypoints(String p)
    {
    	
    	int point = Integer.parseInt(p);
    	double btyPoint = priceTxt*2.5;
    	int round = (int) (btyPoint);
    	if(round == beautyPoint)
    	{
    		
    		System.out.println("Beauty Point is same as Price, Testcase Passed.");
    	}
    	else
    	{
    		System.out.println("Beauty Point is mis-matched, Hence Testcase Failed");
    	}
    	
    }
    public static void functionality() {
    	
    	try
    	{  
    		
    	       
        driver.findElement(By.xpath("/html/body/responsive-design/main/div[1]/div/div/div[4]/div[3]/div/div[3]/button[1]")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/responsive-design/main/div[1]/div/div[1]/div/div[2]/div[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.navigate().refresh();
        driver.get("https://www.douglas.de/viewBasket.html");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement basket = driver.findElement(By.xpath("/html/body/responsive-design/header/div[2]/div[1]/div[1]/div[2]/div[1]/span"));
        int point = Integer.parseInt(basket.getText());
        if(point > 0)
    	{
    		System.out.println("Product has been added to cart successfully, Testcase Passed.");
    	}
    	else
    	{
    		System.out.println("Error in adding the product to cart, Hence Testcase Failed");
    	}
    		
    		}
    	catch (NoSuchElementException e) {
        	System.out.println("Element not found");
        }
       
        
  }
  @AfterTest
  public void afterTest() {
	
	  driver.close();
  }

}
