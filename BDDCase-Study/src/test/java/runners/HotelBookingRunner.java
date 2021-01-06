package runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		features="src//main//resources//features//HotelBooking.feature",
		glue= {"stepdefs"},
		//dryRun=true,
		//strict=true,
		monochrome=true,
		plugin= {"pretty",
				
				"com.cucumber.listener.ExtentCucumberFormatter:target/extent-report/ExtentReport.html"
				
				}
		)


public class HotelBookingRunner {
	
	@AfterClass
	//to write the report after class
	public static void writeExtentReport() {
		//loading the extentcongig.xml file to write the report
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"//src//test//resources//extentconfig.xml"));
		
		//information on the environment where test is run
		Reporter.setSystemInfo("user name", System.getProperty("user.name"));
		Reporter.setSystemInfo("time zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Application name", "Hotel Booking Application ");
		Reporter.setSystemInfo("os name", System.getProperty("os.name").toString());
		Reporter.setSystemInfo("Environment", "Testing Server");
		
	}

}
