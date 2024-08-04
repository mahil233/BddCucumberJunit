package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OffersPage {

public WebDriver driver;
	
	public OffersPage(WebDriver driver) {  //this driver information we r sending from our actual step definition
		this.driver=driver;
	}
	
	private By search=By.xpath("//input[@type='search']");
	private By productName=By.cssSelector("tr td:nth-child(1)");
	
	
	public void searchItem(String name) {
		driver.findElement(search).sendKeys(name);
	}
	
	public void getSearchText() {
		driver.findElement(search).getText();
	}
	
	public String getProductName() {
		return driver.findElement(productName).getText();
	}
}
