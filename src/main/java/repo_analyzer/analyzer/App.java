package repo_analyzer.analyzer;

import java.io.IOException;
import java.util.List;

import repo_analyzer.analyzer.services.S3Service;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	S3Service s3Service = new S3Service();
    	List<String> fileNames = s3Service.listBucketFiles();
    	
    	try {
			s3Service.downloadFile(fileNames.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	System.out.println(fileNames.get(0));
    }
}
