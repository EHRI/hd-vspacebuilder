package eu.ehri.hd_vspacebuilder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TokenizeLearnMaterial {

	public HashMap<String, List<String>> tokenizeDescriptions(
			HashMap<String, List<String>> filesContent) {
		
		System.out.println();
		System.out.println("Begin tokenization");
		
		// HashMap<String, List<List<String>>> tokenLists = new HashMap<String,
		// List<List<String>>>();
		HashMap<String, List<String>> tokenLists = new HashMap<String, List<String>>();
		

		List<String> niod_tokens = new ArrayList<String>();
		List<String> wl_tokens = new ArrayList<String>();
		List<String> yv_tokens = new ArrayList<String>();
		List<String> jmp_tokens = new ArrayList<String>();

		EnglishTokenizer tokenizeInput = new EnglishTokenizer();

		for (String key : filesContent.keySet()) {
			System.out.println("Tokenizing texts of " + key);
			for (int i = 0; i < filesContent.get(key).size(); i++) {
			//	System.out.println("\t\tkey\tSize is " + filesContent.get(key).get(i).length());
				List<String> tokensFile = new ArrayList<String>();
				// for (int idx = 0; idx < filesContent.get(key).get(i).size();
				// idx++) {
				List<String> tokensLine = tokenizeInput.tokenize(filesContent
						.get(key).get(i));
				// .tokenize(filesContent.get(key).get(i).get(idx));

				tokensFile.addAll(tokensLine);
				// }
				if (key.equals("niod")) {
					niod_tokens.addAll(tokensFile);
				}
				if (key.equals("wiener")) {
					wl_tokens.addAll(tokensFile);
				}
				if (key.equals("yadvashem")) {
					yv_tokens.addAll(tokensFile);
				}
				if (key.equals("jewishmuseumprag")) {
					jmp_tokens.addAll(tokensFile);
				}
			}
		}

		tokenLists.put("niod", niod_tokens);
		tokenLists.put("wiener", wl_tokens);
		tokenLists.put("yadvashem", yv_tokens);
		tokenLists.put("jewishmuseumprag", jmp_tokens);

		System.out.println("NIOD tokens: " + niod_tokens.size());
		System.out.println("WIENER tokens: " + wl_tokens.size());
		System.out.println("YV tokens: " + yv_tokens.size());
		System.out.println("JMP tokens: " + jmp_tokens.size());
		
		return tokenLists;

	}

