package pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.TestBase;

public class HotelBookingPage extends TestBase {
	
	
	
	@FindBy(id="txtFirstName")
	WebElement firstname;
	
	@FindBy(id="txtLastName")
	WebElement lastname;
	
	@FindBy(id="txtEmail")
	WebElement email;
	
	@FindBy(id="txtPhone")
	WebElement phone;
	
	@FindBy(xpath="//textarea[@rows=\"5\"]")
	WebElement address;
	
	@FindBy(xpath="//select[@name=\"city\"]")
	WebElement city;
	
	@FindBy(xpath="//select[@name=\"state\"]")
	WebElement state;
	
	@FindBy(xpath="//select[@name=\"persons\"]")
	WebElement guestStaying;
	
	@FindBy(id="txtCardholderName")
	WebElement cardholder;
	
	@FindBy(id="txtDebit")
	WebElement debitcard;
	
	@FindBy(id="txtCvv")
	WebElement cvv;
	
	@FindBy(id="txtMonth")
	WebElement expiryMonth;
	
	@FindBy(id="txtYear")
	WebElement expiryYear;
	
	@FindBy(id="btnPayment")
	WebElement bookingBtn;
	
	public HotelBookingPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public String getTitle() {
		String pagetitle = driver.getTitle();
		return pagetitle;
		
	}
	
	public void name(String fname, String lname) throws InterruptedException 
	{ 
		   
		    Assert.assertEquals("Please fill the First Name", alert());
            firstname.sendKeys(fname);
            Assert.assertEquals("Please fill the Last Name", alert());
            lastname.sendKeys(lname);
       
	}
            
    public void emailId(String Email1, String Email2) throws InterruptedException {   
    	 Assert.assertEquals("Please fill the Email", alert());
    	 email.sendKeys(Email1);
            if(emailIdCheck(Email1)) {
            	 Assert.assertEquals("Please fill the Mobile No.", alert());
            }
            else {
            	Assert.assertEquals("Please enter valid Email Id.", alert());
            	email.clear();
            	
            	email.sendKeys(Email2);
            	if(emailIdCheck(Email2)) {
            		 Assert.assertEquals("Please fill the Mobile No.", alert());
                }
            }
    }
    
    public void phone(String mobileNum1, String mobileNum2) throws InterruptedException {
    	phone.sendKeys(mobileNum1);
    	if(phoneNumValid(mobileNum1));
    	else {
    		Assert.assertEquals("Please enter valid Contact no.", alert());
    		phone.clear();
    		phone.sendKeys(mobileNum2);
    		if(phoneNumValid(mobileNum2));
    	}
    }
    
    public void GuestStaying(String guest) {
    	Select guests = new Select(guestStaying);
    	guests.selectByValue(guest);
	
    }
	
	public void place(String adrss, String City, String State) throws InterruptedException {
		address.sendKeys(adrss);
		
		Assert.assertEquals("Please select city", alert());
		Select selectCity = new Select(city);
		selectCity.selectByVisibleText(City);
		
		Assert.assertEquals("Please select state", alert());
		Select selectState = new Select(state);
		selectState.selectByVisibleText(State);
		
	}
	
	public void CardDetails(String holder, String DebitCard, String CVV, String ExpiryMonth, String ExpiryYear) throws InterruptedException {

		Assert.assertEquals("Please fill the Card holder name", alert());
		cardholder.sendKeys(holder);
		Assert.assertEquals("Please fill the Debit card Number", alert());
		debitcard.sendKeys(DebitCard);
		Assert.assertEquals("Please fill the CVV", alert());
		cvv.sendKeys(CVV);
		Assert.assertEquals("Please fill expiration month", alert());
		expiryMonth.sendKeys(ExpiryMonth);
		Assert.assertEquals("Please fill the expiration year", alert());
		expiryYear.sendKeys(ExpiryYear);
		bookingBtn.click();
	}
	
	
	
	
public String alert() throws InterruptedException {
		
		bookingBtn.click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
	    alert.accept();
	    return text;
		
		}
	
public boolean emailIdCheck(String Email) {

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                        "[a-zA-Z0-9_+&*-]+)*@" + 
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                        "A-Z]{2,7}$"; 
                          
    Pattern pat = Pattern.compile(emailRegex); 
    if (Email == null) 
        return false;
    else
        return pat.matcher(Email).matches(); 

}

public boolean phoneNumValid(String mobileNum) 
{ 
   
    Pattern p = Pattern.compile("[7-9][0-9]{9}"); 
  
    // Pattern class contains matcher() method 
    // to find matching between given number  
    // and regular expression 
    Matcher m = p.matcher(mobileNum); 
    return (m.find() && m.group().equals(mobileNum)); 
} 


	
	}

