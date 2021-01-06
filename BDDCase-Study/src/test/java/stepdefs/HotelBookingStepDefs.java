package stepdefs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import base.TestBase;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.BookingCompletedPage;
import pages.HotelBookingPage;
import pages.LoginPage;

public class HotelBookingStepDefs extends TestBase{
	
    LoginPage loginpage;
    HotelBookingPage hotelbookingpage;
    BookingCompletedPage bookingcompletedpage;
    
	
	@Given("^User is on login Page$")
	public void user_is_on_login_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		TestBase.initialize();
	}


	@Given("^Verify the heading of the page$")
	public void verify_the_heading_of_the_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    loginpage = new LoginPage();
	    Assert.assertTrue(loginpage.verifyHeading());
	    
	}
	
	@When("^User enters Login Credentials$")
	public void user_enters_Login_Credentials() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}


	@Then("^User is on Hotel Booking page$")
	public void user_is_on_Hotel_Booking_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   hotelbookingpage = new HotelBookingPage();
	   Assert.assertEquals("Hotel Booking", hotelbookingpage.getTitle());
	}

	@Then("^User enters the personal details$")
	public void user_enters_the_personal_details() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   hotelbookingpage.name(prop.getProperty("firstname"), prop.getProperty("lastname"));
	}

	@Then("^User enters email Id and mobile number$")
	public void user_enters_email_Id_and_mobile_number(DataTable userCred) throws Throwable {
	
		List<List<String>> data = userCred.raw();
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		hotelbookingpage.emailId(data.get(0).get(0), data.get(0).get(1));
		hotelbookingpage.phone(data.get(1).get(0), data.get(1).get(1));
	}

     @Then("^User enters guests staying and address$")
     public void user_enters_guests_staying_and_address() throws Throwable {
    	 // Write code here that turns the phrase above into concrete actions
    	 hotelbookingpage.GuestStaying(prop.getProperty("guestStaying"));
    	 hotelbookingpage.place(prop.getProperty("address"), prop.getProperty("city"), prop.getProperty("state"));
     }


	@Then("^User enters the payment details$")
	public void user_enters_the_payment_details() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
        hotelbookingpage.CardDetails(prop.getProperty("cardholder"), prop.getProperty("debit"), prop.getProperty("cvv"), prop.getProperty("expiryMonth"), prop.getProperty("expiryYear"));
	}

	@Then("^User is on Booking completed page$")
	public void user_is_on_Booking_completed_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		bookingcompletedpage = new BookingCompletedPage();
	    Assert.assertTrue(bookingcompletedpage.isHeaderDisplayed());
	}
	

	@After(order=1)
	public void afterscenario(Scenario scenario) throws IOException {
		Reporter.addScenarioLog("In case of failure takes screenshot");
		if(scenario.isFailed()) {
			String screenshotname = scenario.getName().replaceAll(" ", "_");
			TakesScreenshot screen = (TakesScreenshot)driver;
			File srcpath = screen.getScreenshotAs(OutputType.FILE);
			File destpath = new File(System.getProperty("user.dir")+"//target//extent-report//"+screenshotname+".png");
			FileUtils.copyFile(srcpath, destpath);		
			Reporter.addScreenCaptureFromPath(destpath.toString());
		}
	}


	@After(order=0)
	public void teardown() {
		driver.close();
	}


}
