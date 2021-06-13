package com.Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"src\\test\\resources\\featureFiles\\contactUsWithexcel.feature"},
		glue= {"StepDefinitionFiles","app.hooks"},
		//tags="not @Skip",
		monochrome = true,
		plugin= {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/failedrerun.txt"
				}
		
		)


public class MyTestRunnerTest {
	


}
