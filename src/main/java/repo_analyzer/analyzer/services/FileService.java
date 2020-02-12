package repo_analyzer.analyzer.services;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class FileService {
	public static final String TEMP_FOLDER = "tmp/";
	public static final String CODE_FOLDER = TEMP_FOLDER + "code/";

	
	public static void createDefaultFolders() {
	   	File tmp = new File(TEMP_FOLDER);

    	if (!tmp.exists()){
    		tmp.mkdir();
        }
	}
	
	public static void unzipFile(String fileName) {
	    String source = TEMP_FOLDER + fileName;

	    try {
	         ZipFile zipFile = new ZipFile(source);
	         zipFile.extractAll(TEMP_FOLDER);
	    } catch (ZipException e) {
	        e.printStackTrace();
	    }
	}
	
	public static String getExtension(String fileName) {
		int i = fileName.lastIndexOf('.');
		if (i > 0) return fileName.substring(i + 1);
		
		return "";
	}
	
	public static String getCodeFolderName(String zipFileName) {
		return zipFileName.substring(0, zipFileName.lastIndexOf('.'));
	}
	
	public static void deleteFile(File file) {
		file.delete();
	}
	
	public static boolean deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    
	    return directoryToBeDeleted.delete();
	}
}
