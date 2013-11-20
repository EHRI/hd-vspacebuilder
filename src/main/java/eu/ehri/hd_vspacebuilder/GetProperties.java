package eu.ehri.hd_vspacebuilder;


import java.io.IOException;
import java.util.Properties;

/*
 * Later separate in collection descriptions folder
 * knowledge, features, etc.
 */

public class GetProperties {

	
	public String getModelFolder(){
		Properties prop = new Properties();	
		
		try {
			prop.load(getClass().getResourceAsStream("config.properties"));
		}catch (IOException ex) {
			ex.printStackTrace();
	    }
		final String MODEL_FOLDER = prop.getProperty("model_folder");
		
		return MODEL_FOLDER;
	}
	public String getDescriptionsFolder(){
		Properties prop = new Properties();	
		
		try {
			prop.load(getClass().getResourceAsStream("config.properties"));
		}catch (IOException ex) {
			ex.printStackTrace();
	    }
		final String DESCRIPTIONS_FOLDER = prop.getProperty("descriptions_folder");
		
		return DESCRIPTIONS_FOLDER;
	}
	public String getTreeTaggerFolder(){
		Properties prop = new Properties();	
		
		try {
			prop.load(getClass().getResourceAsStream("config.properties"));
		}catch (IOException ex) {
			ex.printStackTrace();
	    }
		final String DESCRIPTIONS_FOLDER = prop.getProperty("treetagger_folder");
		
		return DESCRIPTIONS_FOLDER;
	}
	
}

