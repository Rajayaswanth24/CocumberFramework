package cucumberOptions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.AfterClass;

import org.junit.runner.RunWith;

import com.vimalselvam.cucumber.listener.Reporter;
import LibraryFiles.ReUsableLibrary;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(  
	    features = {"Features"},
	    glue= {"stepdefinitions"},
	    plugin= {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:"})
public class TestRunner extends ReUsableLibrary{
	
	@AfterClass
	public static void writeExtentReport() throws IOException {
		System.out.println("runner class");
		String path =getElementFromPropFile("Extentconfigfilepath");
		File File =new File(path);
		Reporter.loadXMLConfig(File);
		 Reporter.setSystemInfo("user", System.getProperty("user.name"));
	        Reporter.setSystemInfo("os", "Windows 7");
	        Reporter.setTestRunnerOutput("Sample test runner output message");
	}



}
