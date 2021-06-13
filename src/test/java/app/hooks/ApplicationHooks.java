package app.hooks;

import java.util.Properties;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.factory.DriverFactory;
import com.utils.ConfigReader;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	
	
	@Before(value="@skip_scenario",order=0)
	public void skip_scenario(Scenario scn) {
		System.out.println("SKIPPED SCENARIO IS: " + scn.getName());
		Assume.assumeTrue(false);
		//This is used to skip test case with tag @skip_scenario from hooks file
	}
	
	
	@Before(order=0)
	public void getProperty() {
		configReader=new ConfigReader();
		prop=configReader.init_prop();		
	}
	
	@Before(order=1)
	public void launchBrowser() {
	String browserName=	prop.getProperty("browser");
	driverFactory=new DriverFactory();
	driver= driverFactory.init_driver(browserName);
		
	}
	
	@After(order=0)
	public void quitBrowser() {		
		driver.quit();
	}
	
	@After(order=1)
	public void teatDown(Scenario scenario) {
		if(scenario.isFailed()) {
			String screenshotName= scenario.getName().replace(" ", "_");
		byte[] sourcePath=((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);			
		}
		
	}
	
}
