package repo_analyzer.analyzer.services;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class FileService {
	public static final String TEMP_FOLDER = "tmp/";
	public static final String CODE_FOLDER = TEMP_FOLDER + "code/";

	
	public static void createDefaultFolders() {
	   	File tmp = new File(TEMP_FOLDER);
    	File code = new File(CODE_FOLDER);

    	if (!tmp.exists()){
    		tmp.mkdir();
    		code.mkdir();
        }
	}
	
	public static void unzipFile(String fileName) {
	    String source = TEMP_FOLDER + fileName;
	    String destination = CODE_FOLDER;

	    try {
	         ZipFile zipFile = new ZipFile(source);
	         zipFile.extractAll(destination);
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
}
