package repo_analyzer.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import repo_analyzer.analyzer.services.S3Service;
import repo_analyzer.analyzer.services.FileService;
import repo_analyzer.analyzer.services.RepoService;


/**
 * Hello world!
 *
 */
public class App {
	private static S3Service s3Service = new S3Service();
	
    public static void main( String[] args ) {
    	List<String> fileNames = s3Service.listBucketFiles();
    	FileService.createDefaultFolders();
    	fileNames.forEach(zipFileName -> run(zipFileName));
    }
    
    public static void run(String zipFileName) {
    	try {
    		System.out.println("Downloading file " + zipFileName);
			s3Service.downloadFile(zipFileName, FileService.TEMP_FOLDER);
			
			System.out.println("Unzip file " + zipFileName);
	    	FileService.unzipFile(zipFileName);

	    	System.out.println("Visit code from " + zipFileName);
			String codeFolderName = FileService.getCodeFolderName(zipFileName);
	    	RepoService.visitCode(FileService.CODE_FOLDER + codeFolderName);
	    	
	    	System.out.println("Deleting zip and code folder");
	    	FileService.deleteDirectory(new File(FileService.CODE_FOLDER + codeFolderName));
	    	FileService.deleteFile(new File(FileService.TEMP_FOLDER + zipFileName));
	    	
			System.out.println(" --- ");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
