package ObjectRepository;

import org.openqa.selenium.By;

public class GoibiboHomePage {

	public static final By oneway=By.xpath("//div/span[@id='oneway']");
	public static final By From=By.id("gosuggest_inputSrc");
	public static final By Fromsuggestion=By.xpath("//input[@id='gosuggest_inputSrc']");
	public static final By destination =By.id("gosuggest_inputDest");
	public static final By destinationsuggestion =By.xpath("//input[@id='gosuggest_inputDest']");
	public static final By DepartureCalender= By.xpath("//*[@id='departureCalendar']");
	public static final By date= By.xpath("//*[@aria-label='Thu Aug 06 2020']");
	public static final By Adultpasbox= By.xpath("//*[@id='adultPaxBox']");
	public static final By Childpasbox= By.xpath("//*[@id='childPaxBox']");
	public static final By Infantpasbox= By.xpath("//*[@id='infantPaxBox']");
	public static final By Travelclass= By.xpath("//*[@class='hypflt-dropDown ico8 slectArr']");
	public static final By PriceList= By.xpath("//*[@data-cy='finalPrice']");
	public static final By BookButton= By.xpath("//*[@class='button fr fltbook fb widthF105 fb quicks']");
	public static final By Ticketdetails= By.xpath("//*[@class='fl mobdn ico18 padL10']");
	public static final By passengerdetails=By.xpath("//*[@id='pax_link_common']");
	public static final By Search=By.xpath("//*[@id='gi_search_btn']");
}

