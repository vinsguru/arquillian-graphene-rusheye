package org.jboss.arquillian.page;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchResult {

    @FindBy(css="h3 a")
    private WebElement resultHeader;

    @FindBy(css="span.st")
    private WebElement resultText;

    public String getResultHeader(){
        return resultHeader.getText();
    }

    public String getResultText(){
        return resultText.getText();
    }
}