package bdd.BDDAddAttributesProcessor;


/* @CucumberOptions(plugin = {"progress", "html:target/cucumber-html-report"},
				features = "src/test/resources",
				tags = "@wip") */

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "progress", "html:target/cucumber-html-report" },
        features = "src/test/resources")
public class RunCukesTestBDD extends AbstractTestNGCucumberTests {
}
