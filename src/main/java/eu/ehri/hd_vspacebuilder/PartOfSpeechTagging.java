package eu.ehri.hd_vspacebuilder;


import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

public class PartOfSpeechTagging {

	static GetProperties property = new GetProperties();
	final String treeTaggerFolder = property.getTreeTaggerFolder();
	
	public List<String> annotatePOSpeech(List<String> tokenized)
			throws IOException, TreeTaggerException {
		// Point TT4J to the TreeTagger installation directory. The executable
		// is expected
		// in the "bin" subdirectory - in this example at
		// "/opt/treetagger/bin/tree-tagger"
		
		
		
		//System.setProperty("treetagger.home", "/home/kepa/Software/TreeTager/");
		System.setProperty("treetagger.home", treeTaggerFolder);
		TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
		final List<String> outputTreeTagger = new ArrayList<String>();

		try {
//			tt.setModel(treeTaggerFolder + "lib/english.par:utf8");
			tt.setModel(treeTaggerFolder + "lib/english.par:utf8");
			tt.setHandler(new TokenHandler<String>() {
				public void token(String token, String pos, String lemma) {
					outputTreeTagger.add(token + "\t" + pos + "\t" + lemma);
					// System.out.println(token + "\t" + pos + "\t" + lemma);
				}
			});
			tt.process(tokenized);
		} finally {
			tt.destroy();
		}
		// System.out.println("SIZE: " + outputTreeTagger.size());
		return outputTreeTagger;
	}

}
