package org.jboss.arquillian.graphene.rusheye.comparator;

import org.jboss.rusheye.suite.ComparisonResult;
import org.openqa.selenium.WebElement;

public interface Ocular {

	ComparisonResult getResult();
	Ocular element(WebElement e);
	
	boolean compare();
	
}
