package com.mos.guser.rows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.mos.lang.rows.RowsLexer;
import com.mos.lang.rows.RowsParser;

public class RowsApplication {

	public void start() throws FileNotFoundException, IOException {
		ANTLRInputStream input = 
				new ANTLRInputStream(
				new FileInputStream("rows.lang"));
		
		RowsLexer lexer = new RowsLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		RowsParser parser = new RowsParser(tokens, 3);
		parser.setBuildParseTree(false);
		parser.file();
		System.out.println("done");
	}

}

