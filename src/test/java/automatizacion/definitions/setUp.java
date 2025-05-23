package automatizacion.definitions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.cucumber.java.AfterStep;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class setUp {
	
	private static AppiumDriver<MobileElement> driver;
	private Scenario scenario;

	public static AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	@Before
	public void setUpScenario(Scenario scenarioContext) throws MalformedURLException {
		scenario = scenarioContext;
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("platformVersion", "13");
		caps.setCapability("platformName", "Android");
		caps.setCapability("deviceName", "Android Device");
		caps.setCapability("appPackage", "com.mercadolibre");
		caps.setCapability("appActivity", "com.mercadolibre.splash.SplashActivity");
		
		String url = "http://127.0.0.1:4723/wd/hub";
		
		System.out.println(caps);
		
		driver = new AppiumDriver<MobileElement>(new URL(url), caps);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterStep
	public void afterStep() {
		generateEvidence(scenario, "[screenshot]", driver);
	}

	@After
	public void tearDown() {
		generateEvidence(scenario, "[FINAL TEST]", driver);
		driver.quit();
	}
	
	
	public static void generateEvidence(Scenario scenario,String imageRefName, AppiumDriver<MobileElement> driver) {
		byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenShot, "image/png", imageRefName);
	}

}
