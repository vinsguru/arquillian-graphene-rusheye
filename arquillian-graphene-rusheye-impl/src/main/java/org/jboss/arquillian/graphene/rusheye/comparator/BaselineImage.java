package org.jboss.arquillian.graphene.rusheye.comparator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import org.boss.arquillian.graphene.rusheye.exception.NoSuchMaskException;
import org.boss.arquillian.graphene.rusheye.exception.NoSuchPatternException;
import org.jboss.arquillian.graphene.rusheye.configuration.RushEyeConfigExporter;
import org.jboss.arquillian.graphene.rusheye.configuration.RushEyeConfiguration;
import org.jboss.rusheye.oneoff.ImageUtils;
import org.jboss.rusheye.suite.Mask;
import org.jboss.rusheye.suite.Perception;
import org.openqa.selenium.WebElement;

class BaselineImage {

    private final RushEyeConfiguration rusheyeConfigurator;
    private final String patternPath;
    private BufferedImage pattern;
    private Perception perception;
    private Set < Mask > masks;

    public BaselineImage(String patternPath, float onePixelTreshold, String[] maskFiles) {
        this.rusheyeConfigurator = RushEyeConfigExporter.get();
        this.patternPath = this.rusheyeConfigurator.getPatternDefaultPath() + File.separator +patternPath;
        this.setPerception(onePixelTreshold);
        this.setMasks(maskFiles);
    }

    public BufferedImage getPattern() {
        this.setPattern(null);
        return this.pattern;
    }

    public BufferedImage getPattern(WebElement element) {
        this.setPattern(element);
        return this.pattern;
    }

    public Perception getPerception() {
        return this.perception;
    }

    public Set < Mask > getMasks() {
        return this.masks;
    }

    private void setPattern(WebElement element) {
        try {
        	File patternImg = new File(this.patternPath);;
        	if(patternImg.exists())
        		this.pattern = ImageIO.read(patternImg);
        	else{
        		if(null != element)
        			this.pattern = DroneImageUtil.getElementSnapshot(element);
        		else 
        			this.pattern = DroneImageUtil.getPageSnapshot();
        		if(this.rusheyeConfigurator.getIfPatternCanBeSaved())
        			ImageUtils.writeImage(this.pattern, patternImg.getParentFile(),patternImg.getName());
        	}
        } catch (IOException e) {
            throw new NoSuchPatternException("Unable to locate pattern @ " + this.patternPath, e);
        }
    }

    private void setPerception(float onePixelTreshold) {
        this.perception = new Perception();
        if (onePixelTreshold == -1f)
            onePixelTreshold = this.rusheyeConfigurator.getPerceptionOnePixelTreshold();
        this.perception.setOnePixelTreshold(onePixelTreshold);
        this.perception.setGlobalDifferenceTreshold(this.rusheyeConfigurator.getPerceptionGlobalDifferenceTreshold());
    }

    private void setMasks(String[] maskFiles) {
        this.masks = new HashSet < Mask > ();
        try {
            for (String maskFile: maskFiles) {
                masks.add(ImageUtils.readMaskImage(new File(this.rusheyeConfigurator.getMaskDefaultPath() + File.separator + maskFile)));
            }
        } catch (Exception e) {
            throw new NoSuchMaskException(maskFiles.toString(), e);
        }
    }

}