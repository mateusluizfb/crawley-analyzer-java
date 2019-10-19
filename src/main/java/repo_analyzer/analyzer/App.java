package repo_analyzer.analyzer;

import repo_analyzer.analyzer.sevices.S3Service;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	new S3Service().listBucketFiles();
    }
}
