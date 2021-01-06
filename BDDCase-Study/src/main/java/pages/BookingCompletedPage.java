package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class BookingCompletedPage extends TestBase {
	
	@FindBy(xpath="//h1[contains(text(), 'Booking Completed!')]")
	WebElement bookingCompleted;
	
	
	public BookingCompletedPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean isHeaderDisplayed() {
		return bookingCompleted.isDisplayed();
	}

	
}
