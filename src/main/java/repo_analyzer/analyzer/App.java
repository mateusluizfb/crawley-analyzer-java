package repo_analyzer.analyzer;

import java.io.IOException;
import java.util.List;

import repo_analyzer.analyzer.services.S3Service;
import repo_analyzer.analyzer.services.RepoService;


/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	S3Service s3Service = new S3Service();
    	List<String> fileNames = s3Service.listBucketFiles();
    	
    	try {
			s3Service.downloadFile(fileNames.get(0), RepoService.TEMP_FOLDER);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	// RepoService.unzipFile(fileNames.get(0));
    	// RepoService.visitCode("tmp/");
    }
}
