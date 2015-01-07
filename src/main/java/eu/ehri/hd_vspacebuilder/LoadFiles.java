package eu.ehri.hd_vspacebuilder;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadFiles {


	public HashMap<String, List<String>> loadFiles4Model(String descriptionsFolder) {
		File descriptions = new File(descriptionsFolder);
		System.out.println("Loading files...");
		List<String> niod_lines = new ArrayList<String>();
		List<String> wl_lines = new ArrayList<String>();
		List<String> yv_lines = new ArrayList<String>();
		List<String> jmp_lines = new ArrayList<String>();
		List<String> ushmm_lines = new ArrayList<String>();
		List<String> bundesarchiv_lines = new ArrayList<String>();
		List<String> its_lines = new ArrayList<String>();
		List<String> cegesoma_lines = new ArrayList<String>();
		List<String> ifz_lines = new ArrayList<String>();
		
		Pattern textFile = Pattern.compile(".*txt");
		List<String> fileList = new ArrayList<String>();
		HashMap<String, List<String>> filesContent = new HashMap<String, List<String>>();

		for (final File fileEntry : descriptions.listFiles()) {
			Matcher matcherText = textFile.matcher(fileEntry.toString());
			if (matcherText.find()) {
				fileList.add(fileEntry.getName());
			}
		}

		for (int i = 0; i < fileList.size(); i++) {
			String currentLine;
			List<String> linesOfFile = new ArrayList<String>();
			BufferedReader br = null;

			try {
				br = new BufferedReader(new FileReader(descriptionsFolder
						+ fileList.get(i)));

				while ((currentLine = br.readLine()) != null) {
					linesOfFile.add(currentLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//provisional names of institutions
			if (fileList.get(i).matches("niod_.*")) {
				niod_lines.addAll(linesOfFile);
			}
			if (fileList.get(i).matches("wiener_.*")) {
				wl_lines.addAll(linesOfFile);
			}
			if (fileList.get(i).matches("yadvashem_.*")) {
				yv_lines.addAll(linesOfFile);
			}
			if (fileList.get(i).matches("jewishmuseumprag_.*")) {
				jmp_lines.addAll(linesOfFile);
			}
			if (fileList.get(i).matches("Bundesarchiv_.*")) {
				bundesarchiv_lines.addAll(linesOfFile);
			}
			if (fileList.get(i).matches("ushmm_.*")) {
				ushmm_lines.addAll(linesOfFile);
			}
			if (fileList.get(i).matches("its_.*")) {
				its_lines.addAll(linesOfFile);
			}
			if (fileList.get(i).matches("cegesoma_.*")) {
				cegesoma_lines.addAll(linesOfFile);
			}
			if (fileList.get(i).matches("ifz_.*")) {
				ifz_lines.addAll(linesOfFile);
			}

		}

		filesContent.put("niod", niod_lines);
		filesContent.put("wiener", wl_lines);
		filesContent.put("yadvashem", yv_lines);
		filesContent.put("jewishmuseumprag", jmp_lines);
		filesContent.put("bundesarchiv", bundesarchiv_lines);
		filesContent.put("ushmm", ushmm_lines);
		filesContent.put("its", its_lines);
		filesContent.put("cegesoma", cegesoma_lines);
		filesContent.put("ifz", ifz_lines);
		
		System.out.println("NIOD lines: " + niod_lines.size());
		System.out.println("WL lines: " + wl_lines.size());
		System.out.println("YV lines: " + yv_lines.size());
		System.out.println("JMP lines: "  + jmp_lines.size());
		System.out.println("BA lines: "  + bundesarchiv_lines.size());
		System.out.println("USHMM lines: "  + ushmm_lines.size());
		System.out.println("ITS lines: "  + its_lines.size());
		System.out.println("CEGESOMA lines: "  + cegesoma_lines.size());
		System.out.println("IFZ lines: "  + ifz_lines.size());


		return filesContent;
	}

}
