package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

/**
 * TODO: Define which feature files to run. Optionally specify which tags to run.
 * */
@Test
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepdefinitions",
    tags = "@positive"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
