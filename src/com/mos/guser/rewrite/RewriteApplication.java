
package com.mos.guser.rewrite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.mos.guser.ToTclListConverter;
import com.mos.lang.rewrite.JavaLexer;
import com.mos.lang.rewrite.JavaParser;

public class RewriteApplication {

	public void start() throws FileNotFoundException, IOException {
		ANTLRInputStream input = new ANTLRInputStream(
				new FileInputStream("Rewrite.lang"));
		
		JavaLexer lexer = new JavaLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		JavaParser parser = new JavaParser(tokens);
		
		ParseTree tree = parser.compilationUnit();
		
		InsertSerialID inserter = new InsertSerialID(tokens);
		
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(inserter, tree);
		System.out.println(inserter.rewriter.getText());
	}

}
