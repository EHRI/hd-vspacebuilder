package eu.ehri.hd_vspacebuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.annolab.tt4j.TreeTaggerException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, TreeTaggerException
    {
    	
    	String folderName = args[0];

    	//Load collection and file descriptions
    	LoadFiles upload = new LoadFiles();
		HashMap<String, List<String>> uploaded = upload
				.loadFiles4Model(folderName);
		
		//Tokenize the texts
		TokenizeLearnMaterial tokenizeMaterial = new TokenizeLearnMaterial();
		HashMap<String, List<String>> tokenized = tokenizeMaterial
				.tokenizeDescriptions(uploaded);
		uploaded.clear();
		
		//Annotate part of speech
		POSannotateLernMaterial annotatePOS = new POSannotateLernMaterial();
		HashMap<String, List<String>> annotated = annotatePOS.annotatePOS(tokenized);
		
		//Extract relevant features for each institution
		//Infer the features of the vector space
		ExtractRelevantFeatures extractFeats = new ExtractRelevantFeatures();
		HashMap<String, List<String>> extracted = extractFeats
				.extractFeatures(annotated);
		
		//Build vectorial representation of each institution with TF*IDF
		RankTfIdf rankFeats = new RankTfIdf();
		HashMap<String, HashMap<String, Double>> tfidf = rankFeats
				.rankingTfIdf(extracted);
		
		//END
        System.out.println( "FINISH!" );
    }
}
