package org.jboss.arquillian.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.graphene.rusheye.annotation.RushEye;
import org.jboss.arquillian.graphene.rusheye.annotation.Snap;
import org.jboss.arquillian.graphene.rusheye.comparator.Ocular;
import org.openqa.selenium.By;

import java.util.List;

@Snap("GoogleSearchResults.png")
public class GoogleSearchResults {
	
	@Root
	private GrapheneElement root;

    @FindByJQuery(".rc")
    private List<GoogleSearchResult> results;

    @RushEye
    private Ocular ocular;
    
    public int getCount(){
        return results.size();
    }

    public void show(){
        Graphene.waitGui().until().element(By.className("rc")).is().present();
        System.out.println("\nResults:\n");
        for(GoogleSearchResult result: results)
            System.out.println(result.getResultHeader());
    }
    
    public void compare(){
    	System.out.println(ocular.element(root).compare());
    }
}
