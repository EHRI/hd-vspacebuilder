Command line application to build the vectorial space for the integrated help desk

Installation:
1) Download TreeTagger from http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/
2) Add the right path to the TreeTagger folder in the config.properties
3) Compile: mvn compile assembly:single

Use:
1) Create a directory for the descriptions (i.e. ./Descriptions) and put all the collection descriptions or texts used to build the model in the folder
2) Create an empty directory for the produced model. 
3) Run the application java -jar hd-vspace-0.0.1-SNAPSHOT-jar-with-dependencies.jar Descriptions model

