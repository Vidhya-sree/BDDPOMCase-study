package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(xpath="//h1[contains(text(), 'Hotel Booking Application')]")
	WebElement heading;
	
	@FindBy(name="userName")
	WebElement userName;
	
	@FindBy(name="userPwd")
	WebElement password;
	
	@FindBy(xpath="//input[@class=\"btn\"]")
	WebElement loginbtn;
	
	@FindBy(xpath="//div[@id=\"userErrMsg\"]")
	WebElement usernameError;
	
	@FindBy(xpath="//div[@id=\"pwdErrMsg\"]")
	WebElement pwdError;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public boolean verifyHeading() {
		
	 return heading.isDisplayed(); 
	 }
	
	public void login(String uname, String pwd) throws InterruptedException {
		
		loginbtn.click();
		Thread.sleep(1000);
		Assert.assertEquals("* Please enter userName.", usernameError.getText());
	    userName.sendKeys(uname);
		
		loginbtn.click();
		Thread.sleep(1000);
		Assert.assertEquals("* Please enter password.", pwdError.getText());
		password.sendKeys(pwd);
	
		loginbtn.click();
	}
	

}
