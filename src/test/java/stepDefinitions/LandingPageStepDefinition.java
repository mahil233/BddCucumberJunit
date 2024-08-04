package stepDefinitions;

import io.cucumber.java.en.Given;

import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import utils.TestContextSetup;

public class LandingPageStepDefinition {
	public WebDriver driver; //declare globally so that we can access it anywhere
	public String landingPageProductName;
	public String offersPageProductName;
	TestContextSetup testContextSetup;
	LandingPage landingPage;
	
	public LandingPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup=testContextSetup;
		this.landingPage=testContextSetup.pageObjectManager.getLandingPage();
	}
	
	
	@Given("User is on GreenCart Landing page")
	public void user_is_on_green_cart_landing_page() {
		Assert.assertTrue(landingPage.getTitleLandingPage().contains("GreenKart"));	
	}
	
	@When("^user searched with Shortname (.+) and extracted actual name of product$")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {
		//create object of landing page & call to landing page object methods
		landingPage.searchItem(shortName);
		Thread.sleep(2000);
	    testContextSetup.landingPageProductName=landingPage.getProductName().split("-")[0].trim();  //split is used because we want only tomato
	    System.out.println(landingPageProductName+" is extracted from home page");
	}
	
	@When("Added {string} items of the selected product to cart")
	public void Added_items_product(String quantity)
	{
		
		landingPage.incrementQuantity(Integer.parseInt(quantity)); //quantity is in string format, we convert into int using Integer.parseInt()
		landingPage.addToCart();
		
	}
}
