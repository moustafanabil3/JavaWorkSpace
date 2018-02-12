package com.mos.guser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.mos.lang.arrayinit.ArrayInitLexer;
import com.mos.lang.arrayinit.ArrayInitParser;

public class ArrayInitApplication {

	public void start() throws FileNotFoundException, IOException {
		
		ANTLRInputStream input = new ANTLRInputStream(
				new FileInputStream("langfile.lang"));
		
		ArrayInitLexer lexer = new ArrayInitLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		ArrayInitParser parser = new ArrayInitParser(tokens);
		
		ParseTree tree = parser.init();
		
		System.out.println(tree.toStringTree());
		
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new ToTclListConverter(), tree);
		System.out.println();
	}

}
