package stepDefinations;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;
import com.vimalselvam.cucumber.listener.Reporter;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.GoibiboHomePage;

@RunWith(Cucumber.class)
public class stepDefination extends ReUsableLibrary{

	@Given("^User opens the Goibibo Website$")
	public void user_opens_the_Goibibo_Website() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		BasicConfigurator.configure();
		CreateLogger.tCreateLogger();
		CreateLogger.LOGGER.info("Suite STARTED");

		
	
		//Reporter.addStepLog("Suite Started");
		System.out.println("Suite Started");
		OpenWDInstance();
		String url=getElementFromPropFile("Websiteurl");
		launchApplication(url);
		//Reporter.addStepLog("Application Launched");
		
	    //throw new PendingException();
	}

	@When("^User entered valid input details and click on search button$")
	public void user_entered_valid_input_details_and_click_on_search_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 String filepath=getElementFromPropFile("TestdataFilePath");
			
			File jsonfilepath=new File(filepath);
			JSONParser jsonParser = new JSONParser();
			FileReader reader = new FileReader(jsonfilepath);
			//Read JSON file
			JSONObject obj = (JSONObject)jsonParser.parse(reader);
			JSONObject jsonobject =(JSONObject) obj;
			String Source=jsonobject.get("Source").toString();
			driver.findElement(GoibiboHomePage.oneway).click();
			CreateLogger.LOGGER.info("Selected one way Trip");
			custom3Sleep();
			driver.findElement(GoibiboHomePage.From).sendKeys(jsonobject.get("Source").toString());
		custom9Sleep();
		    driver.findElement(GoibiboHomePage.Fromsuggestion).sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		    CreateLogger.LOGGER.info("Entered destination Source as - "+jsonobject.get("Source").toString());
		    driver.findElement(GoibiboHomePage.destination).sendKeys(jsonobject.get("Destination").toString());
		    
			custom9Sleep();
			    driver.findElement(GoibiboHomePage.destinationsuggestion).sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
			    CreateLogger.LOGGER.info("Entered destination details as - "+jsonobject.get("Destination").toString());
			     driver.findElement(GoibiboHomePage.DepartureCalender).click();
			     
		    custom3Sleep();
		  
		    driver.findElement(GoibiboHomePage.date).click();
		    CreateLogger.LOGGER.info("Selected Departure Date");
		    custom3Sleep();
		    driver.findElement(GoibiboHomePage.passengerdetails).click();
		    custom3Sleep();
		    driver.findElement(GoibiboHomePage.Adultpasbox).clear();
		    driver.findElement(GoibiboHomePage.Adultpasbox).sendKeys(jsonobject.get("AdultsCount").toString());
		    driver.findElement(GoibiboHomePage.Childpasbox).clear();
		    driver.findElement(GoibiboHomePage.Childpasbox).sendKeys(jsonobject.get("Childcount").toString());
		    driver.findElement(GoibiboHomePage.Infantpasbox).clear();
		    driver.findElement(GoibiboHomePage.Infantpasbox).sendKeys(jsonobject.get("Infantscount").toString());
		    CreateLogger.LOGGER.info("Entered Passenger Details");
		  WebElement TravelClass=  driver.findElement(GoibiboHomePage.Travelclass);
		//  Select sl=new Select (TravelClass);
		 // sl.selectByVisibleText("Business");
		    driver.findElement(GoibiboHomePage.Search).click();
		    CreateLogger.LOGGER.info("");
		    custom9Sleep();
		    
		        	 }
         
			

	@Then("^User will be routed to Booking page and click on book option$")
	public void user_will_be_routed_to_review_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 List<WebElement> PriceList=new ArrayList<WebElement>();
		 PriceList=driver.findElements(GoibiboHomePage.PriceList);
		 List<Integer> FinalpriceList=new ArrayList<Integer>();
		 List<Integer> SortedFinalpriceList=new ArrayList<Integer>();
		 int Totalflightscount=PriceList.size();
		 System.out.println("price count - "+Totalflightscount);
         for(int i=1;i<=Totalflightscount;i++) {
        	  
        	 String Finalprice=driver.findElement(By.xpath("(//*[@data-cy='finalPrice'])["+i+"]")).getText();
        	 System.out.println("final price-"+Finalprice);
        	 String Fprice=Finalprice.replace(",", "");
        	 System.out.println("TicketPrice -"+ Fprice);
         	
        	int TicketPrice=Integer.parseInt(Fprice);
        	System.out.println("TicketPrice -"+ TicketPrice);
        	
        	 
        	FinalpriceList.add(TicketPrice);
        	SortedFinalpriceList.add(TicketPrice);
			 
		 }
         
        Collections.sort(SortedFinalpriceList);
         int lowestprice=SortedFinalpriceList.get(0);
         System.out.println("Lowest price in the List -"+lowestprice);
         CreateLogger.LOGGER.info("Lowest Ticket Price -"+lowestprice);
         
         for(int j=0;j<Totalflightscount;j++) {
        	 if(lowestprice==FinalpriceList.get(j)) {
        		 int k=j-1;
        		 driver.findElement(By.xpath("(//*[@class='button fr fltbook fb widthF105 fb quicks'])["+k+"]")).click();
        		 
        		  CreateLogger.LOGGER.info("Clicked on the book option with low Ticket Price");

        	 }
         }
	   
	}    
	@And("^Booking Details will be displayed$")
	public void booking_Details_will_be_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String parentwindow=driver.getWindowHandle();
		 Set<String> tabs=driver.getWindowHandles();
	      Iterator<String> ite=tabs.iterator();
	      
	      while(ite.hasNext()){
	    	  String childwindow=ite.next();
	    	  if(!childwindow.equals(parentwindow)) {
		    	  driver.switchTo().window(childwindow);
		    	
	    	  driver.switchTo().window(childwindow);
	    	String Ticketdetails=  driver.findElement(GoibiboHomePage.Ticketdetails).getText();
	    	
	    	
	    	  System.out.println("Ticket Details "+Ticketdetails);
	    	  CreateLogger.LOGGER.info("Ticket Details -"+Ticketdetails);
	    	  
	    	  }
	      }
	    
	   
	}
    }
