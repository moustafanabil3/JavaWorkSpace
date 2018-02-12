package com.mos.guser.labeledexpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.mos.lang.arrayinit.ArrayInitLexer;
import com.mos.lang.arrayinit.ArrayInitParser;
import com.mos.lang.labeledexpr.LabeledExprLexer;
import com.mos.lang.labeledexpr.LabeledExprParser;

public class LabeledExprApplication {

	public void start() throws FileNotFoundException, IOException {
		
		ANTLRInputStream input = new ANTLRInputStream(
				new FileInputStream("labeledExpr.lang"));
		
		LabeledExprLexer lexer = new LabeledExprLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LabeledExprParser parser = new LabeledExprParser(tokens);
		ParseTree tree = parser.prog();
		tree.accept(new EvalVisitor());
		
		
	}

}
