package eu.ehri.hd_vspacebuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ClusterSynonyms {

	public String substituteSynonym(String word) {

		InputStream inputStreamSynonyms = getClass().getResourceAsStream(
				"/synonyms.dic");
		String feature;

		// Create dictionary of synonyms
		HashMap<String, String> dictionary = new HashMap<String, String>();

		BufferedReader br = null;
		String currentLine;
		try {

			br = new BufferedReader(new InputStreamReader(inputStreamSynonyms));
			while ((currentLine = br.readLine()) != null) {
				String[] splittedLine = currentLine.split("\t");
				dictionary.put(splittedLine[0], splittedLine[1]);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		if (dictionary.containsKey(word)){
			feature = dictionary.get(word);
		}else{
			feature = word;
		}
		
		
		return feature;
	}

}
