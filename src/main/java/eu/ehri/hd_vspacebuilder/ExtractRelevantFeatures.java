package eu.ehri.hd_vspacebuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ExtractRelevantFeatures {

	//static GetProperties property = new GetProperties();

	
	
	public HashMap<String, List<String>> extractFeatures(
			HashMap<String, List<String>> tagged, String outputfolder) throws IOException {
		System.out.println();
		System.out.println("EXTRACT RELEVANT FEATURES");
		
		InputStream inputStreamStopWords = getClass().getResourceAsStream("/genericStopWords.list");
		
		BufferedReader br = null;
		String currentLine;
		List<String> stopWordsList = new ArrayList<String>();
		try {

			br = new BufferedReader(new InputStreamReader(inputStreamStopWords));
			while ((currentLine = br.readLine()) != null) {
				stopWordsList.add(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
//		System.out.println("SIZE STOP " + stopWordsList.size());

		HashMap<String, List<String>> selected = new HashMap<String, List<String>>();
		List<String> features = new ArrayList<String>();

		List<String> niod_feats = new ArrayList<String>();
		List<String> wl_feats = new ArrayList<String>();
		List<String> yv_feats = new ArrayList<String>();
		List<String> jmp_feats = new ArrayList<String>();
		List<String> ba_feats = new ArrayList<String>();
		List<String> ushmm_feats = new ArrayList<String>();
		List<String> its_feats = new ArrayList<String>();

		for (String key : tagged.keySet()) {
			 System.out.println("Extracting features from "+ key);
			for (int i = 0; i < tagged.get(key).size(); i++) {
				if (tagged.get(key).get(i).toLowerCase().matches("^[a-z].*")) {
					String[] splitted = tagged.get(key).get(i).split("\t");
					if (splitted[1].matches("NN.*")
							|| splitted[1].matches("NP.*")|| splitted[1].equals("JJ")
							|| splitted[1].matches("VV.*")) {
						if (splitted[2].equals("<unknown>")) {
							if (!stopWordsList.contains(splitted[0]) && (splitted[0].length()>2) && !(splitted[0].equals("\n"))) {
								ClusterSynonyms clusterSyns = new ClusterSynonyms();
								String feature = clusterSyns.substituteSynonym(splitted[0].toLowerCase());
								if (key.equals("niod")) {
									niod_feats.add(feature);
								}
								if (key.equals("wiener")) {
									wl_feats.add(feature);
								}
								if (key.equals("yadvashem")) {
									yv_feats.add(feature);
								}
								if (key.equals("jewishmuseumprag")) {
									jmp_feats.add(feature);
								}
								if (key.equals("bundesarchiv")) {
									ba_feats.add(feature);
								}
								if (key.equals("ushmm")) {
									ushmm_feats.add(feature);
								}
								if (key.equals("its")) {
									its_feats.add(feature);
								}
							}
							// }
						} else {
							if (!stopWordsList.contains(splitted[2])&& (splitted[2].length()>2) && !(splitted[2].equals("\n"))) {
								ClusterSynonyms clusterSyns = new ClusterSynonyms();
								String feature = clusterSyns.substituteSynonym(splitted[2].toLowerCase());
								if (key.equals("niod")) {
									niod_feats.add(feature);
								}
								if (key.equals("wiener")) {
									wl_feats.add(feature);
								}
								if (key.equals("yadvashem")) {
									yv_feats.add(feature);
								}
								if (key.equals("jewishmuseumprag")) {
									jmp_feats.add(feature);
								}
								if (key.equals("bundesarchiv")) {
									ba_feats.add(feature);
								}
								if (key.equals("ushmm")) {
									ushmm_feats.add(feature);
								}
								if (key.equals("its")) {
									its_feats.add(feature);
								}
							}
						}
					}
				}
			}
		}
		// }
		selected.put("niod", niod_feats);
		selected.put("wiener", wl_feats);
		selected.put("yadvashem", yv_feats);
		selected.put("jewishmuseumprag", jmp_feats);
		selected.put("bundesarchiv", ba_feats);
		selected.put("ushmm", ushmm_feats);
		selected.put("its", its_feats);
		
		features.addAll(niod_feats);
		features.addAll(wl_feats);
		features.addAll(yv_feats);
		features.addAll(jmp_feats);
		features.addAll(ba_feats);
		features.addAll(ushmm_feats);
		features.addAll(its_feats);
		
	//	System.out.println("Features size begin " + features.size());
		AuxiliaryMethods.removeDuplicates(features);
		Collections.sort(features);

		File featuresFile = new File(outputfolder + "features.txt");

		FileWriter fw = new FileWriter(featuresFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < features.size(); i++) {
			bw.write(features.get(i));
			bw.newLine();
		}

		bw.close();

		System.out.println("Dimensions of the vector space: " + features.size());

		return selected;

	}

}
