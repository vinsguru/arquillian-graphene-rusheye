package org.jboss.arquillian.graphene.rusheye.comparator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.boss.arquillian.graphene.rusheye.exception.RushEyeVisualComparisonException;
import org.jboss.arquillian.graphene.rusheye.configuration.RushEyeConfigExporter;
import org.jboss.arquillian.graphene.rusheye.configuration.RushEyeConfiguration;
import org.jboss.rusheye.comparison.ImageComparator;
import org.jboss.rusheye.core.DefaultImageComparator;
import org.jboss.rusheye.oneoff.ImageUtils;
import org.jboss.rusheye.suite.ComparisonResult;
import org.openqa.selenium.WebElement;

public final class OcularImpl implements Ocular {

    private final boolean isPageFragment;
    private final ImageComparator comparator;
    private final BaselineImage baseline;
    private final RushEyeConfiguration rusheyeConfigurator;
    private final String patternFileName;
    
    private WebElement element;
    private ComparisonResult result;
    

    public OcularImpl(final boolean isPageFragment, final String pattern, final float onePixelTreshold, final String[] maskFiles) throws IOException {
        this.isPageFragment = isPageFragment;
        this.patternFileName = pattern;
        this.baseline = new BaselineImage(pattern, onePixelTreshold, maskFiles);
        this.comparator = new DefaultImageComparator();
        this.rusheyeConfigurator = RushEyeConfigExporter.get();
    }

    public Ocular element(WebElement ele) {
        this.element = ele;
        return this;
    }

    public boolean compare() {
        result = comparator.compare(this.getPattern(), this.getSample(), this.baseline.getPerception(), this.baseline.getMasks());
        System.out.println(result);
        File outputfile = new File(this.rusheyeConfigurator.getResultDefaultPath() + File.separator + this.patternFileName);
        try {
            ImageUtils.writeImage(result.getDiffImage(), outputfile.getParentFile(), outputfile.getName());
        } catch (IOException e) {
            throw new RushEyeVisualComparisonException("Unable to write the difference", e);
        }
        return result.isEqualsImages();
    }

    private BufferedImage getPattern() {
        if (this.isPageFragment)
            return this.baseline.getPattern(this.element);
        else
            return this.baseline.getPattern();
    }

    private BufferedImage getSample() {
        if (this.isPageFragment)
            return DroneImageUtil.getElementSnapshot(this.element);
        else
            return DroneImageUtil.getPageSnapshot();
    }

    public ComparisonResult getResult() {
        return result;
    }

}