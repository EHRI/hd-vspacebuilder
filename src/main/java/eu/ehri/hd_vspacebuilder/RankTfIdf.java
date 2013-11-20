package eu.ehri.hd_vspacebuilder;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RankTfIdf {

	// In the next implementation it should be shorter and divided
	// in several methods implemented in different classes.

	static GetProperties property = new GetProperties();
	final static String modelFolder = property.getModelFolder();
	final File featuresFile = new File("features.txt");
	
	public HashMap<String, HashMap<String, Double>> rankingTfIdf(
			HashMap<String, List<String>> extractedFeats) {
		System.out.println();
		System.out.println("Begin ranking of features");
		
		// Read files features.txt and store the features in a list
		List<String> featuresList = new ArrayList<String>();
		String currentLine;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(modelFolder
					+ featuresFile));
			while ((currentLine = br.readLine()) != null) {
				featuresList.add(currentLine);
				// System.out.println(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		// Compute feature occurrences
		// and length of each document
		HashMap<String, HashMap<String, Integer>> featureOccurrence = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> lengths = new HashMap<String, Integer>();

		for (String key : extractedFeats.keySet()) {
			//System.out.println("Building representation of: " + key);
			int length = extractedFeats.get(key).size();
			HashMap<String, Integer> featuresCount = new HashMap<String, Integer>();
			for (int i = 0; i < extractedFeats.get(key).size(); i++) {
				if (featuresCount.containsKey(extractedFeats.get(key).get(i))) {
					int value = featuresCount.get(extractedFeats.get(key)
							.get(i));
					featuresCount
							.put(extractedFeats.get(key).get(i), value + 1);
				} else {
					featuresCount.put(extractedFeats.get(key).get(i), 1);
				}
			}

			featureOccurrence.put(key, featuresCount);
			lengths.put(key, length);
			//System.out.println("LENGTH OF " + key + " " + length);
		}

		// Compute Term Frequencies
		// declaration of TF hashmap for all the descriptions
		HashMap<String, HashMap<String, Double>> termFrequencies = new HashMap<String, HashMap<String, Double>>();

		for (String key : featureOccurrence.keySet()) {
			HashMap<String, Double> frequenciesDocument = new HashMap<String, Double>();
			// extract maximal frequency of a feature. It will be used for
			// normalization of the term frequency
			Double max = (double) Collections.max(featureOccurrence.get(key)
					.values());
			for (String feat : featureOccurrence.get(key).keySet()) {
				Double tfvalue = featureOccurrence.get(key).get(feat) / max;
				frequenciesDocument.put(feat, tfvalue);
				// System.out.println("Easy variante:\tTF\t" + feat + "\t" +
				// "OCCUR\t" + featureOccurrence.get(key).get(feat) + "/" + max
				// + "\t"+ tfvalue);
				// System.out.println();
			}
			termFrequencies.put(key, frequenciesDocument);

		}

		// Compute Inverse Document Frequencies
		HashMap<String, HashMap<String, Double>> idfs = new HashMap<String, HashMap<String, Double>>();
		List<String> institutions = new ArrayList<String>();
		institutions.addAll(termFrequencies.keySet());
		// here for loop and chech the IDF :-)
		for (int i = 0; i < institutions.size(); i++) {
			HashMap<String, Double> institutionIdf = new HashMap<String, Double>();
			for (String key : termFrequencies.get(institutions.get(i)).keySet()) {
				double inDocs = 0;
				double idf = 0;
				for (int index = 0; index < institutions.size(); index++) {
					if (termFrequencies.get(institutions.get(index))
							.containsKey(key)) {
						inDocs++;
					}
				}
				idf = Math.log(1 + institutions.size() / inDocs);
				institutionIdf.put(key, idf);
				// System.out.println(key + "\t" + idf);
			}
			idfs.put(institutions.get(i), institutionIdf);
		}

		// ADD checks and print it out
		// begin thinking about document to understand the classes.

		HashMap<String, HashMap<String, Double>> tfidfs = new HashMap<String, HashMap<String, Double>>();

		for (int i = 0; i < institutions.size(); i++) {
			HashMap<String, Double> institutionTfIdf = new HashMap<String, Double>();
			for (String key : termFrequencies.get(institutions.get(i)).keySet()) {
				double tf = termFrequencies.get(institutions.get(i)).get(key);
				double idf = idfs.get(institutions.get(i)).get(key);
				double tfidf = tf * idf;
				institutionTfIdf.put(key, tfidf);
				//System.out.println(key + "\t" + tfidf);
			}
			tfidfs.put(institutions.get(i), institutionTfIdf);
		}

		// write tfidf files
		for (String key : tfidfs.keySet()) {
			String filename = key + ".tfidf";
			try {
				FileWriter fstream = new FileWriter(modelFolder + filename);
				BufferedWriter out = new BufferedWriter(fstream);

				for (String feat : tfidfs.get(key).keySet()){
					out.write(feat + "\t" +tfidfs.get(key).get(feat));
					out.newLine();
				}
				out.close();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());

			}

		}

		System.out.println("Vector space built!!");
		return tfidfs;
	}

}
