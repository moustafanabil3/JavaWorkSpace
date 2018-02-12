package com.mos.guser.rewrite;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

import com.mos.lang.rewrite.JavaBaseListener;
import com.mos.lang.rewrite.JavaParser.ClassBodyContext;
import com.mos.lang.rewrite.JavaParser.ClassBodyDeclarationContext;

public class InsertSerialID extends JavaBaseListener {
	public TokenStreamRewriter rewriter;
	
	public InsertSerialID (CommonTokenStream tokens) {
		this.rewriter = new TokenStreamRewriter(tokens);
	}
	
//	@Override
//	public void enterClassBodyDeclaration
//		this.rewriter.insertBefore(ctx.start, "\tpublic final int serialVersionUID;");
//	}

	@Override
	public void enterClassBody(ClassBodyContext ctx) {
		this.rewriter.insertBefore(ctx.classBodyDeclaration(0).start, "\n\t/*Hello Moustafa*/\n");
	}

}
