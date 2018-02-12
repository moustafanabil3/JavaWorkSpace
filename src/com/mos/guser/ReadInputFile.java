package com.mos.guser;

import java.io.IOException;

import com.mos.guser.labeledexpr.LabeledExprApplication;
import com.mos.guser.labeledexprwithclear.LabeledExprWithClearApplication;
import com.mos.guser.rewrite.RewriteApplication;
import com.mos.guser.rows.RowsApplication;

public class ReadInputFile {

	public static void main(String[] args) {
		ArrayInitApplication arrayInitApp = new ArrayInitApplication();
		LabeledExprApplication labeledExprApp = new LabeledExprApplication();
		LabeledExprWithClearApplication labeledExprWithClearApp =
				new LabeledExprWithClearApplication();
		RowsApplication rowsApp = new RowsApplication();
		RewriteApplication rewriteApp = new RewriteApplication();

		try {
			arrayInitApp.start();
			System.out.println("+ -------------------");
			labeledExprApp.start();
			System.out.println("+ -------------------");
			labeledExprWithClearApp.start();
			System.out.println("+ -------------------");
			rowsApp.start();
			System.out.println("+ -------------------");
			rewriteApp.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
