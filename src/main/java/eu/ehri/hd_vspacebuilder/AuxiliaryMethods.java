package eu.ehri.hd_vspacebuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AuxiliaryMethods {


	/*
	 * remove duplicate members of a list of strings
	 */
	static List<String> removeDuplicates(List<String> arrayList) {
		HashSet<String> set = new HashSet<String>(arrayList);
		arrayList.clear();
		arrayList.addAll(set);
		return arrayList;
	}

	/*
	 * remove duplicate members of a list of integers
	 */
	static List<Integer> removeDuplicateIndexes(List<Integer> arrayList) {
		HashSet<Integer> set = new HashSet<Integer>(arrayList);
		arrayList.clear();
		arrayList.addAll(set);
		return arrayList;
	}

	/*
	 * transform a list of strings in a string. Members of the list are in ""
	 * and separated by space
	 */
	static String listToString(List<String> arrayList) {
		String string = "";
		for (int i = 0; i < arrayList.size() - 1; i++) {
			string = string + arrayList.get(i) + " ";
		}
		if (arrayList.size() > 0) {
			string = string + arrayList.get(arrayList.size() - 1);
		}
		return string;
	}

	/*
	 * transform a list of strings in a string. Members of the list are
	 * separated by space
	 */
	static String listToString2(List<String> arrayList) {
		String string = "";
		for (int i = 0; i < arrayList.size() - 1; i++) {
			string = string + arrayList.get(i) + " ";
		}
		if (arrayList.size() > 0) {
			string = string + arrayList.get(arrayList.size() - 1);
		}
		return string;
	}
	
	/*
	 * sorts a hash by value
	 */
	
	public static HashMap<String, Double> sortByValue(HashMap<String, Double> map) {
        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
/*
            public int compare(Map.Entry<String, Double> m1, Map.Entry<String, Double> m2) {
                System.out.println(m2.getValue() + " " + m1.getValue());
//        		(m2.getValue()).compareTo(m1.getValue());
            	return Double.compare(m2.getValue(), m1.getValue());
            }
  */          
            public int compare(Map.Entry<String, Double> m1, Map.Entry<String, Double> m2) {
            	int out = 0;
            	if (m2.getValue() < m1.getValue()){
            		out = -1;
            	}
               	if (m2.getValue() > m1.getValue()){
               		out = 1;
            	}
               	if (m2.getValue() == m1.getValue()){
               		out = 0;
               	}
               	return out;
            }
      	});

        HashMap<String, Double> result = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

	
}