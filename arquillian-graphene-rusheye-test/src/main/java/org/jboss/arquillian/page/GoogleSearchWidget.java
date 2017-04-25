package org.jboss.arquillian.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.graphene.rusheye.annotation.Snap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Snap("GoogleSearchWidget.png")
public class GoogleSearchWidget {
	
	@Root
	private GrapheneElement root;

    @FindBy(name="q")
    private WebElement searchBox;

    @FindBy(name="btnG")
    private WebElement searchButton;

    public void searchFor(String searchString){
        searchBox.clear();

        //Google makes ajax calls during search
        int length = searchString.length();
        searchBox.sendKeys(searchString.substring(0, length-1));
        Graphene.guardAjax(searchBox).sendKeys(searchString.substring(length-1));
    }

    public void search(){
        Graphene.guardAjax(searchButton).click();
    }
}