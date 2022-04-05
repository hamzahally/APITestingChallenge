package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/main/resources"}, glue = {"stepdefs"}, publish = true)
public class RunCucumberTest {
}
