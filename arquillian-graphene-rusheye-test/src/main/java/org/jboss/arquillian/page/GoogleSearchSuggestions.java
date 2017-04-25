package org.jboss.arquillian.page;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.graphene.rusheye.annotation.RushEye;
import org.jboss.arquillian.graphene.rusheye.annotation.Snap;
import org.jboss.arquillian.graphene.rusheye.comparator.Ocular;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


@Snap("GoogleSearchSuggestions.png")
public class GoogleSearchSuggestions {
	
	@Root
	private GrapheneElement root;
   
    @FindBy(css="li div.sbqs_c")
    private List<WebElement> suggesions;
    
    @RushEye
    private Ocular ocular;

    public int getCount(){
        return suggesions.size();
    }

    public void show(){
        for(WebElement s: suggesions)
            System.out.println(s.getText());
    }
    
    public void compare(){
    	System.out.println(ocular.element(root).compare());
    }

}