package com.qa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber.html", "summary"},
        snippets = CAMELCASE,
        dryRun = false,  // MUST BE FALSE
        monochrome = true,
        features = {"src/test/resources"},
        glue = {"com.qa.stepdef"}
)
public class MyRunnerTest {
    // All initialization moved to Hooks
}