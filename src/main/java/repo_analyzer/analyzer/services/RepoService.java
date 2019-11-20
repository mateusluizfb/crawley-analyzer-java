package repo_analyzer.analyzer.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import repo_analyzer.analyzer.visitors.ErlangBaseVisitor;
import repo_analyzer.analyzer.visitors.ErlangLexer;
import repo_analyzer.analyzer.visitors.ErlangParser;

public class RepoService {
	public static final String TEMP_FOLDER = "tmp/";
	
	public static void unzipFile(String fileName) {
	    String source = "tmp/" + fileName;
	    String destination = "tmp/code";

	    try {
	         ZipFile zipFile = new ZipFile(source);
	         zipFile.extractAll(destination);
	    } catch (ZipException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void visitCode(String folderPath) {
		File f = new File(folderPath);
		File[] matchingFiles = f.listFiles();
		System.out.println(folderPath);
		
		
		String tempPath;
		File tempFile;
		File[] tempMatchingFiles;

		for(int i = 0; i < matchingFiles.length; i++) {
			tempPath = matchingFiles[i].getPath();
			tempFile = new File(tempPath);
			tempMatchingFiles = tempFile.listFiles();
			if(tempMatchingFiles != null) {
				System.out.println(matchingFiles[i].getName() + " é pasta!");

			}
		}

		// runParser("")
	}
	
	private static void runParser(String filePath) {
		try {
			InputStream inputstream = new FileInputStream(filePath);
			ErlangLexer lexer = new ErlangLexer(new ANTLRInputStream(inputstream));
			ErlangParser parser = new ErlangParser(new CommonTokenStream(lexer));
		    new ErlangBaseVisitor().visitForms(parser.forms());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
