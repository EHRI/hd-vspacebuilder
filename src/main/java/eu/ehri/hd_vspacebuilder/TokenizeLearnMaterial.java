package eu.ehri.hd_vspacebuilder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TokenizeLearnMaterial {

	public HashMap<String, List<String>> tokenizeDescriptions(
			HashMap<String, List<String>> filesContent) {
		
		System.out.println();
		System.out.println("Begin tokenization");
		

		HashMap<String, List<String>> tokenLists = new HashMap<String, List<String>>();
		

		List<String> niod_tokens = new ArrayList<String>();
		List<String> wl_tokens = new ArrayList<String>();
		List<String> yv_tokens = new ArrayList<String>();
		List<String> jmp_tokens = new ArrayList<String>();
		List<String> ba_tokens = new ArrayList<String>();
		List<String> ushmm_tokens = new ArrayList<String>();

		EnglishTokenizer tokenizeInput = new EnglishTokenizer();

		for (String key : filesContent.keySet()) {
			System.out.println("Tokenizing texts of " + key);
			for (int i = 0; i < filesContent.get(key).size(); i++) {
				List<String> tokensFile = new ArrayList<String>();
				List<String> tokensLine = tokenizeInput.tokenize(filesContent
						.get(key).get(i));

				tokensFile.addAll(tokensLine);
				
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
				if (key.equals("bundesarchiv")) {
					ba_tokens.addAll(tokensFile);
				}
				if (key.equals("ushmm")) {
					ushmm_tokens.addAll(tokensFile);
				}
			}
		}

		tokenLists.put("niod", niod_tokens);
		tokenLists.put("wiener", wl_tokens);
		tokenLists.put("yadvashem", yv_tokens);
		tokenLists.put("jewishmuseumprag", jmp_tokens);
		tokenLists.put("bundesarchiv", ba_tokens);
		tokenLists.put("ushmm", ushmm_tokens);

		System.out.println("NIOD tokens: " + niod_tokens.size());
		System.out.println("WIENER tokens: " + wl_tokens.size());
		System.out.println("YV tokens: " + yv_tokens.size());
		System.out.println("JMP tokens: " + jmp_tokens.size());
		System.out.println("BA tokens: " + ba_tokens.size());
		System.out.println("USHMM tokens: " + ushmm_tokens.size());
		
		return tokenLists;
	}
	}

