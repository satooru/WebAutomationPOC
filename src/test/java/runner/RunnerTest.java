package runner;

import org.junit.runner.RunWith;

import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utils.WebDriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepDefinition", "hooks"},
        plugin = {"pretty"},
        features = {"src/test/resources/"},
        dryRun = false,
        tags = {"@correios"})
public class RunnerTest {
}
