package com.health.bdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		monochrome = true,
		features = "classpath:features",
			plugin = {
					"pretty", "html:target/cucmber-reports",
					"json:target/cucumber.json",
					"rerun:target/rerun.txt" }//To create text file with failed scenarios
					,tags = "@Testcase_10001"
		)

public class TestRunnerSuite {}
