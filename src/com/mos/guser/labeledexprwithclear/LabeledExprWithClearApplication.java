package com.mos.guser.labeledexprwithclear;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearLexer;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser;

public class LabeledExprWithClearApplication {

	public void start() throws FileNotFoundException, IOException {
		ANTLRInputStream input = new ANTLRInputStream(
				new FileInputStream("labeledExprWithClear.lang"));
		
		LabeledExprWithClearLexer lexer = new LabeledExprWithClearLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LabeledExprWithClearParser parser = new LabeledExprWithClearParser(tokens);
		ParseTree tree = parser.prog();
		tree.accept(new EvalVisitor());
	}

}
