package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	public TestBase() {
		prop = new Properties();
		try {
			FileInputStream  fin = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//config//config.properties");
			prop.load(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void initialize() {
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\selenium\\webdrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\selenium\\webdrivers\\gecko.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
	}
}
