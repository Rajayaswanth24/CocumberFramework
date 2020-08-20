package stepDefinations;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	
	@Before
	public void beforevaldiation()
	{
		System.out.println("Before Ticket   book");
	}
	
	@After
	public void Aftervaldiation()
	{
		System.out.println("  After Ticket book ");
	}
		
			}

