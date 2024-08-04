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
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContextSetup;

public class OfferPageStepDefinition {
	public WebDriver driver; //declare globally so that we can access it anywhere
	public String landingPageProductName;
	public String offersPageProductName;
	TestContextSetup testContextSetup;
	PageObjectManager pageObjectManager;
	
	public OfferPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup=testContextSetup;
	}
	
	@Then("^user searched for (.+) shortname in offers page$")
	public void user_searched_for_shortname_in_offers_page(String shortName) throws InterruptedException {
	   switchToOffersPage();
	   OffersPage offerPage=testContextSetup.pageObjectManager.offersPage();
	   offerPage.searchItem(shortName);
	   Thread.sleep(2000);
	   offersPageProductName=offerPage.getProductName();
	   Thread.sleep(2000);
	}
	
	public void switchToOffersPage() {
		//if we are in below url then we need to switch it
		//if(testContextSetup.driver.getCurrentUrl().equalsIgnoreCase("https://rahulshettyacademy.com/seleniumPractise/#/offers"));
		
		//create object of landing page so that we can call to selectTopDealsPage() method which is present in landingPage
		LandingPage landingPage=testContextSetup.pageObjectManager.getLandingPage();
		landingPage.selectTopDealsPage();
		testContextSetup.genericUtils.SwitchWindowToChild();
		
	}
	
	@Then("validate product name in offers page matches with Landing Page")
	public void validate_product_name_in_offers_page_matches_with_landing_page() {
	    Assert.assertEquals(offersPageProductName, testContextSetup.landingPageProductName);
	}
}
