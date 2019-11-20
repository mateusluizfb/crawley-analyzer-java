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
    	
    	fileNames.forEach(fileName -> {
    		System.out.println("Downloading file " + fileName);
    		/*
        	try {
    			s3Service.downloadFile(fileName, RepoService.TEMP_FOLDER);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	
    		System.out.println("Unzip file " + fileName);
        	RepoService.unzipFile(fileName);
        	*/
    		System.out.println("Visit code from " + fileName);
    		String folderName = fileName.substring(0, fileName.lastIndexOf('.'));
        	RepoService.visitCode(RepoService.TEMP_FOLDER + "code/" + folderName);
    		System.out.println("");
    	});
    }
}
