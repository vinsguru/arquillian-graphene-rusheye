package org.arquillian.graphene.rusheye.test;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.page.GooglePage;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

@RunAsClient
public class RushEyeTest extends Arquillian{
	
	@Page
	GooglePage google;

	@Test
	public void storeBaseLineImages( ){	
		
		Graphene.goTo(GooglePage.class);
		google.compare();
		
		
		google.getSearchWidget().searchFor("rusheye");
		google.getSuggestions().compare();
		
		google.getSearchWidget().search();
		
		google.getSearchResults().compare();
	}
	
	
	@Test
	public void compareBaseLineImages( ){	
		
		Graphene.goTo(GooglePage.class);
		google.compare();
		
		
		google.getSearchWidget().searchFor("rusheye");
		google.getSuggestions().compare();
		
		google.getSearchWidget().search();
		
		google.getSearchResults().compare();
	}

}
