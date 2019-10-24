package repo_analyzer.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import repo_analyzer.analyzer.services.S3Service;
import repo_analyzer.analyzer.visitors.ErlangBaseVisitor;
import repo_analyzer.analyzer.visitors.ErlangLexer;
import repo_analyzer.analyzer.visitors.ErlangParser;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	/*
    	S3Service s3Service = new S3Service();
    	List<String> fileNames = s3Service.listBucketFiles();
    	
    	try {
			s3Service.downloadFile(fileNames.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	System.out.println(fileNames.get(0));
    	*/
    	
    	/*
    	InputStream inputstream;
		try {
			inputstream = new FileInputStream("tmp/helloword.erl");
			ErlangLexer lexer = new ErlangLexer(new ANTLRInputStream(inputstream));
			ErlangParser parser = new ErlangParser(new CommonTokenStream(lexer));
		    new ErlangBaseVisitor().visitForms(parser.forms());
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

    }
}
