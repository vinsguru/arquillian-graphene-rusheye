package org.jboss.arquillian.graphene.rusheye.configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.boss.arquillian.graphene.rusheye.exception.RushEyeConfigurationException;

public class RushEyeConfiguration extends Configuration{
	
	private String basePath = "src/main/resources";
	private String patternPath = "/pattern";
	private String maskPath =  "/mask";
	private String resultPath =  "/result";
	private final float onePixelTrehold = 0f;
	private final float globalTreshold = 0f;
	private final boolean createBaseline = true;  //if not exists
    private final boolean updateBaseline = false; //if the result is false
	
	public Path getDefaultPath(){
		return Paths.get(getProperty("basePath", this.basePath));
	}
	
	public void setDefaultPath(String path){
		setProperty("basePath", path);
	}
	
	public Path getPatternDefaultPath(){
		return Paths.get(this.getDefaultPath().toString(),getProperty("patternPath", this.patternPath));
	}
	
	public void setPatternDefaultPath(String path){
		setProperty("patternPath", path);
	}
	
	public Path getMaskDefaultPath(){
		return Paths.get(this.getDefaultPath().toString(),getProperty("maskPath", this.maskPath));
	}	
	
	public void setMaskDefaultPath(String path){
		setProperty("maskPath", path);
	}	
		
	public Path getResultDefaultPath(){
		return Paths.get(this.getDefaultPath().toString(),getProperty("resultPath", this.resultPath));
	}

	public void setResultDefaultPath(String path){
		setProperty("resultPath", path);
	}
	
	public float getPerceptionOnePixelTreshold(){
		return Float.parseFloat(getProperty("perceptionOnePixelTreshold", Float.toString(this.onePixelTrehold)));
	}
	
	public void setPerceptionOnePixelTreshold(float treshold){
		setProperty("perceptionOnePixelTreshold", Float.toString(treshold));
	}
	
	public float getPerceptionGlobalDifferenceTreshold(){
		return Float.parseFloat(getProperty("perceptionGlobalDifferenceTreshold", Float.toString(this.globalTreshold)));
	}
	
	public void setPerceptionGlobalDifferenceTreshold(float treshold){
		setProperty("perceptionGlobalDifferenceTreshold", Float.toString(treshold));
	}
	
	public boolean getIfPatternCanBeSaved(){
		return Boolean.valueOf(getProperty("createBaseline", Boolean.toString(this.createBaseline)));
	}
	
	public void setIfPatternCanBeSaved(boolean createBaseline){
		setProperty("createBaseline", Boolean.toString(createBaseline));
	}

	public boolean getIfPatternCanBeUpdated(){
		return Boolean.valueOf(getProperty("updateBaseline", Boolean.toString(this.updateBaseline)));
	}
	
	public void setIfPatternCanBeUpdated(boolean updateBaseline){
		setProperty("updateBaseline", Boolean.toString(updateBaseline));
	}	
	
	@Override
	public void validate() throws RushEyeConfigurationException {
		System.out.println(this.getPatternDefaultPath());
		System.out.println(this.getMaskDefaultPath());
		System.out.println(this.getResultDefaultPath());
		if( !(Files.exists(this.getPatternDefaultPath())) &&
		    Files.exists( this.getMaskDefaultPath()) && 
		    Files.exists(this.getResultDefaultPath()) )
			throw new RushEyeConfigurationException("RushEye pattern/mask/result path not found");		
	}

}
