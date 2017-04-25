package org.jboss.arquillian.page;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.jboss.arquillian.graphene.rusheye.annotation.RushEye;
import org.jboss.arquillian.graphene.rusheye.annotation.Snap;
import org.jboss.arquillian.graphene.rusheye.comparator.Ocular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Snap("GooglePage.png")
@Location("https://www.google.com")
public class GooglePage {
	
    @Drone
    private WebDriver driver;

    @FindBy(id = "sfdiv")   //sbtc
    private GoogleSearchWidget searchWidget;

    @FindBy(id="rso")
    private GoogleSearchResults results;

    @FindBy(css="div.sbsb_a")
    private GoogleSearchSuggestions suggestions;

    @RushEye
    private Ocular ocular;
    
    public boolean isAt(){
        return driver.getTitle().equals("Google");
    }

    public GoogleSearchWidget getSearchWidget(){
        Graphene.waitGui().until().element(By.id("sbtc")).is().present();
        return searchWidget;
    }

    public GoogleSearchResults getSearchResults(){
        return results;
    }

    public GoogleSearchSuggestions getSuggestions(){
        return suggestions;
    }
    
    public void compare(){
    	System.out.println(ocular.compare());
    }
	
}
