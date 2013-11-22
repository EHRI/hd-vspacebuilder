package eu.ehri.hd_vspacebuilder;

import java.io.IOException;
import java.util.Properties;

public class GetProperties {

	public String getTreeTaggerFolder() {
		Properties prop = new Properties();

		try {
			prop.load(getClass().getResourceAsStream("/config.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		final String TREETAGGER_FOLDER = prop.getProperty("treetagger_folder");

		return TREETAGGER_FOLDER;
	}

}
