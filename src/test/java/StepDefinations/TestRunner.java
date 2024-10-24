package StepDefinations;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features", glue= {"StepDefinations"},
					monochrome=true, plugin={"pretty","html:target/HTMLReports/report.html",
							                          "json:target/JsonReports/report.json"}
				)
					
	
public class TestRunner {

}
 