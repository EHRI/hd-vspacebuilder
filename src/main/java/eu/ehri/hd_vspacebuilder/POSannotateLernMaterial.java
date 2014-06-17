package eu.ehri.hd_vspacebuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.annolab.tt4j.TreeTaggerException;

public class POSannotateLernMaterial {

	public HashMap<String, List<String>> annotatePOS(
			HashMap<String, List<String>> tokens) throws IOException,
			TreeTaggerException {
		System.out.println();
		System.out.println("BEGIN POS ANNOTATION");
		HashMap<String, List<String>> annotated = new HashMap<String, List<String>>();

		List<String> niod_pos = new ArrayList<String>();
		List<String> wl_pos = new ArrayList<String>();
		List<String> yv_pos = new ArrayList<String>();
		List<String> jmp_pos = new ArrayList<String>();
		List<String> ba_pos = new ArrayList<String>();
		List<String> ushmm_pos = new ArrayList<String>();
		List<String> its_pos = new ArrayList<String>();

		for (String key : tokens.keySet()) {
			System.out.println("ANNOTATING " + key);
			List<String> annotation = new ArrayList<String>();
			PartOfSpeechTagging annotate = new PartOfSpeechTagging();
			annotation = annotate.annotatePOSpeech(tokens.get(key));
			//
			if (key.equals("niod")) {
				niod_pos.addAll(annotation);
			}
			if (key.equals("wiener")) {
				wl_pos.addAll(annotation);
			}
			if (key.equals("yadvashem")) {
				yv_pos.addAll(annotation);
			}
			if (key.equals("jewishmuseumprag")) {
				jmp_pos.addAll(annotation);
			}
			if (key.equals("bundesarchiv")) {
				ba_pos.addAll(annotation);
			}
			if (key.equals("ushmm")) {
				ushmm_pos.addAll(annotation);
			}
			if (key.equals("its")) {
				its_pos.addAll(annotation);
			}
		}

		System.out.println("POS NIOD SIZE " + niod_pos.size());
		System.out.println("POS WL SIZE " + wl_pos.size());
		System.out.println("POS YV SIZE " + yv_pos.size());
		System.out.println("POS JMP SIZE " + jmp_pos.size());
		System.out.println("POS BA SIZE " + ba_pos.size());
		System.out.println("POS USHMM SIZE " + ushmm_pos.size());
		System.out.println("POS ITS SIZE " + its_pos.size());
		annotated.put("niod", niod_pos);
		annotated.put("wiener", wl_pos);
		annotated.put("yadvashem", yv_pos);
		annotated.put("jewishmuseumprag", jmp_pos);
		annotated.put("bundesarchiv", ba_pos);
		annotated.put("ushmm", ushmm_pos);
		annotated.put("its", its_pos);
		
		return annotated;

	}
}
