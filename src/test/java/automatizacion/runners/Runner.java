package automatizacion.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(glue = { "automatizacion.definitions" }, 
				features = "src/test/resources/features",
				tags = "@test1 or @test3",
				plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
				)

public class Runner {

}
 