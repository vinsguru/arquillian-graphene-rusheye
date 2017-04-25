package org.jboss.arquillian.graphene.rusheye.comparator;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jboss.arquillian.drone.api.annotation.Default;
import org.jboss.arquillian.graphene.context.GrapheneContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

final class DroneImageUtil {
	
	private static WebDriver driver = GrapheneContext.getContextFor(Default.class).getWebDriver();
	
	public static BufferedImage getPageSnapshot(){
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage page = null;
		try {
			page = ImageIO.read(screen);
		} catch (Exception e) {
			throw new RuntimeException("Unable to get page snapshot", e);
		}
		return page;
	}
	
	public static BufferedImage getElementSnapshot(WebElement element){
		Point p = element.getLocation();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		return getPageSnapshot().getSubimage(p.getX(), p.getY(), width, height);
	}
	
	
	public static BufferedImage maskElement(WebElement element){
		return null;
	}
	
	public static BufferedImage maskElement(BufferedImage img, WebElement element){
		return null;
	}
}
