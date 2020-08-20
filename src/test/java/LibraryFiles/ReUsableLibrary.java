package LibraryFiles;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.CreateLogger;

import junit.framework.Assert;

public class ReUsableLibrary {
	public static  String propFilePath= System.getProperty("user.dir")+"\\GlobalSettings.properties";
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	private static SimpleDateFormat strScreenShotName=new SimpleDateFormat("MMddyy_HHmmss");
   public static String browser;
   private static String strHtmlDir = new SimpleDateFormat("MMddyy").format(new Date());

   private static SimpleDateFormat strExtentReportName = new SimpleDateFormat("MMddyy_HHmmss");

	
	public static String getElementFromPropFile(String property) throws IOException{
		
		FileInputStream file =new FileInputStream(propFilePath);
		Properties prop=new Properties();
		prop.load(file);
		return prop.getProperty(property);
	}
	
	public static void createDir(String dirName){
		File f= new File(dirName);
		try{
			if(!f.exists()){
				f.mkdir();
				CreateLogger.LOGGER.info("Directory Created :: "+dirName);
			}
		}
			catch (Throwable e){
				
				CreateLogger.LOGGER.info("Unable to create Directory  :: "+dirName);
			}
		}
	public static void takeScreenShot(ITestResult result) throws IOException {

		String testCaseName = result.getName();

		File file = null;

		if (result.getStatus() == ITestResult.SUCCESS)

		file = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + testCaseName + "\\Passed\\");

		if (result.getStatus() == ITestResult.FAILURE)

		file = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + testCaseName + "\\Failed\\");

		if (result.getStatus() == ITestResult.SKIP)

		file = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + testCaseName + "\\Skipped\\");

		if (!file.exists()) {

		System.out.println("File created " + file);

		file.mkdir();

		}

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {

		String screenshotName = testCaseName + strScreenShotName.format(new Date()) + ".png";

		File targetFile = new File(file, screenshotName);

		FileUtils.copyFile(scrFile, targetFile);

		} catch (IOException e) {

		e.printStackTrace();

		}

		}

	

		public void custom9Sleep() {

		try {

		Thread.sleep(9000);

		} catch (InterruptedException e) {

		e.printStackTrace();

		}

		}
		
		public static void customSleep() {

			try {

			Thread.sleep(1000);

			} catch (InterruptedException e) {

			e.printStackTrace();

			}

			}
		
		public static void custom3Sleep() {

			try {

			Thread.sleep(3000);

			} catch (InterruptedException e) {

			e.printStackTrace();

			}

			}

	

		public static void OpenWDInstance() throws IOException {

		try {

		browser = getElementFromPropFile("Browser");

		if (browser.equals("Firefox")) {

		String GeckoDriverPath = getElementFromPropFile("GeckoDriverPath");

		System.setProperty("webdriver.gecko.driver", GeckoDriverPath);

		driver = new FirefoxDriver();

		} else if (browser.equals("IE")) {

		String ieDriverPath = getElementFromPropFile("InternetExplorerDriverPath");

		System.setProperty("webdriver.ie.driver", ieDriverPath);

		driver = new InternetExplorerDriver();

		} else if (browser.equals("Chrome")) {

		String userDir=getElementFromPropFile("ChromeDriverpath");

		/*String downloadFilepath = userDir + "\\DownloadCases\\";

		System.out.println(downloadFilepath);

		createDir(downloadFilepath);

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		chromePrefs.put("profile.default_content_settings.popups", 0);

		chromePrefs.put("download.default_directory", downloadFilepath);

		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("prefs", chromePrefs);

		options.addArguments("--test-type");

		options.addArguments("--disable-extensions"); //to disable browser extension popup

		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/

		//String chromeDriverPath =userDir+"//SupportingSoftware//chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", userDir);

		driver = new ChromeDriver();

		} else if (browser.equals("PhantomJS")) {

		String phantomJSexePath = getElementFromPropFile("PhantomJSPath");

		File file = new File(phantomJSexePath);

		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());

		try {

		driver = new PhantomJSDriver();

		} catch (UnreachableBrowserException e) {

		System.out.println(e);

		driver = new PhantomJSDriver();

		}

		}

		driver.manage().window().maximize();
		custom3Sleep();

		System.out.println("Created Driver Instance and Launched Browser: " + browser);
		CreateLogger.LOGGER.info("Created Driver Instance and Launched Browser: " + browser);

		

		} catch (Exception e) {

	//	logger.log(LogStatus.FAIL, "Failed To Create Driver Instance " + browser + " " + e.toString());

		Assert.fail("Failed To Create Driver Instance " + browser + " " + e.toString());

		}

		}

		
		public static void removeDir(String dirName){
			File f= new File(dirName);
					String[]entries= f.list();
			for(String s: entries){
				File currentFile = new File (f.getPath(),s);
				currentFile.delete();
			}
			f.delete();
		}
		@SuppressWarnings("deprecation")
		public static void launchApplication(String url) {


		//logger.log(LogStatus.INFO, "Launched Browser " + browser + " Successfully ");

		String appURL=url;

	
		driver.get(appURL);

		custom3Sleep();

		String body = driver.findElement(By.xpath("/html/body")).getText();

		CreateLogger.LOGGER.info("Opened the application with URL -"+appURL);
	

		}
		
		public int  lowestPrice(ArrayList<Integer> list) {
			
			int listsize=list.size();
			for (int i=1;i<=listsize;i++) {
				
			}
		
			
			return 0;
			
			
			
			
		}

		
		
		public void ExtentReportConfig() throws IOException {

			try {

			// Extent Report Configuration

			String environment = getElementFromPropFile("Environment");

			String projectName = getElementFromPropFile("ProjectName");

			String release = getElementFromPropFile("Release");

			String moduleName = getElementFromPropFile("ModuleName");

			String strEnv = ReUsableLibrary.getElementFromPropFile("Environment");

			String strEnvDir = ".//TestReports//HTMLReports//" + strEnv;

			ReUsableLibrary.createDir(strEnvDir);

			String strModuleDir = strEnvDir + "//" + release;

			ReUsableLibrary.createDir(strModuleDir);

			String htmlDirPath = strModuleDir + "//" + strHtmlDir;

			ReUsableLibrary.createDir(htmlDirPath);

			String htmlReportPath = htmlDirPath;

			String file = htmlReportPath + "\\STMExtentReport" + strExtentReportName.format(new Date()) + ".html";

			extent = new ExtentReports(file, false);

			extent.addSystemInfo("Project Name", projectName).addSystemInfo("Environment", environment).addSystemInfo("Release", release).addSystemInfo("Module Name", moduleName);

			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

			System.out.println("Extent Report Configuration Completed");

			} catch (Exception e) {

			logger.log(LogStatus.FAIL, "Failed To Configure Extent Reports" + e.toString());

			Assert.fail("Failed To Configure Extent Reports" + e.toString());

			}

			}

	}

