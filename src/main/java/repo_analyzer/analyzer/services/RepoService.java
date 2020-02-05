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

	public static void visitCode(String folderPath) {		
		File file = new File(folderPath);
		File[] matchingFiles = file.listFiles();
		
		if(matchingFiles == null) {
			runParser(folderPath);
			return;
		}

		for (int i = 0; i < matchingFiles.length; i++) {
			visitCode(matchingFiles[i].getPath());
		}
	}
	
	private static void runParser(String filePath) {
		String extension = FileService.getExtension(filePath);

		if(!extension.equals("erl")) return;
				
		System.out.println("Visiting " + filePath);
		
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
