package task.two.StartWars;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StarWarsWeb {

	ChromeDriver dr;
	
	@BeforeMethod
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "src/test/resources/Driver/chromedriver.exe");
		dr= new ChromeDriver();
	}
	
	@Test
	public void testAll() throws InterruptedException {
	
		dr.get("https://www.starwars.com/");
		//Validate if the title of the page matches the actual title 
		Assert.assertEquals("StarWars.com | The Official Star Wars Website", dr.getTitle());
		
		//Validate if the log in button is displayed and enabled 
		WebElement LogIn = dr.findElementByXPath("/html/body/div[3]/div[2]/span/div/div[4]/div[2]/div[1]/div[1]");
		Assert.assertTrue(LogIn.isDisplayed());
		Assert.assertTrue(LogIn.isEnabled());
		
		//Validate if the SignUp button is displayed and enabled 
		WebElement SignUp = dr.findElementByXPath("/html/body/div[3]/div[2]/span/div/div[4]/div[2]/div[1]/div[2]");
		Assert.assertTrue(SignUp.isDisplayed());
		Assert.assertTrue(SignUp.isEnabled());
		
		//Validate if the search button is displayed and enabled 
		WebElement SearchButton = dr.findElementById("nav-search-icon");
		Assert.assertTrue(SearchButton.isDisplayed());
		Assert.assertTrue(SearchButton.isEnabled());
		

		//Navigate to Flims pagef and validate the URL
		dr.findElementByXPath("/html/body/div[3]/div[2]/div[1]/nav/ul[1]/li[4]/a/span[2]").click();
		Assert.assertEquals(dr.getCurrentUrl(),"https://www.starwars.com/films");
		//Validate the title for the films' page
		System.out.println("Title of Flims Page: " + dr.getTitle());
		Assert.assertEquals("Star Wars Movies | StarWars.com", dr.getTitle());
		
		//Click on Video and validate the URL
		dr.findElementByXPath("/html/body/div[3]/div[2]/div[1]/nav/ul[1]/li[2]/a/span[2]").click();
		Assert.assertEquals(dr.getCurrentUrl(),"https://www.starwars.com/video");
		//Validate the title for the Video page
		System.out.println("Title of Video Page: " + dr.getTitle());
		Assert.assertEquals("Star Wars Videos and Behind the Scenes Featurettes | StarWars.com", dr.getTitle());
	}
	
	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(10);
		dr.quit();
	}
	
}
