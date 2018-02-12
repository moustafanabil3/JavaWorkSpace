package com.mos.guser.labeledexprwithclear;

import java.util.HashMap;

import com.mos.lang.labeledexprwihtclear.*;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.AddSubContext;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.AssignContext;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.ClearCmdContext;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.IdContext;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.IntContext;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.MulDivContext;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.ParensContext;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.PrintExprContext;
import com.mos.lang.labeledexprwihtclear.LabeledExprWithClearParser.ProgContext;

public class EvalVisitor extends LabeledExprWithClearBaseVisitor<Integer> {
	HashMap<String, Integer> identifiers;
	
	public EvalVisitor() {
		super();
		this.identifiers = new HashMap<String, Integer>();
	}
	
	@Override
	public Integer visitProg(ProgContext ctx) {
		super.visitProg(ctx);
		System.out.println("done");
		return null; // it can return a status number where there are errors or not
	}
	
	@Override
	public Integer visitPrintExpr(PrintExprContext ctx) {
		Integer value = this.visit(ctx.expr());
		System.out.println("+----");
		System.out.println("Expr: " + ctx.expr().getText());
		System.out.println("Equals: " + value);
		System.out.println("+----");
		return null;
	}
	
	@Override
	public Integer visitMulDiv(MulDivContext ctx) {
		Integer result;
		Integer leftOperand = visit(ctx.expr(0));
		Integer rightOperand = visit(ctx.expr(1));
		if (ctx.op.getType() == LabeledExprWithClearParser.MUL) {
			result = leftOperand * rightOperand;
		} else { // DIV
			result = leftOperand / rightOperand;
		}
		
		return result;
	}
	
	@Override
	public Integer visitAddSub(AddSubContext ctx) {
		Integer result;
		Integer leftOperand = visit(ctx.expr(0));
		Integer rightOperand = visit(ctx.expr(1));
		if (ctx.op.getType() == LabeledExprWithClearParser.ADD) {
			result = leftOperand + rightOperand;
		} else { // SUB
			result = leftOperand - rightOperand;
		}
		
		return result;
	}
	
	@Override
	public Integer visitParens(ParensContext ctx) {
		return visit(ctx.expr());
	}
	
	@Override
	public Integer visitAssign(AssignContext ctx) {
		String parameterName = ctx.ID().getText();
		
		if (this.identifiers.containsKey(parameterName)) {
			System.out.println("Warning: " + parameterName + " already defined. Overriding its value."); // mnabil : test variable already exists
		}
		
		Integer value = visit(ctx.expr());
		this.identifiers.put(parameterName, value);
		return null;
	}
	
	@Override
	public Integer visitId(IdContext ctx) {
		if (this.identifiers.containsKey(ctx.getText())) {
			return this.identifiers.get(ctx.getText());
		} else {
			System.out.println("Warning: Can't find parameter: " + ctx.getText());
			return 0;
		}
	}
	
	@Override
	public Integer visitInt(IntContext ctx) {
		return Integer.valueOf(ctx.INT().getText());
	}
	
	@Override
	public Integer visitClearCmd(ClearCmdContext ctx) {
		System.out.println("Warning: Clearing all parameters");
		this.identifiers.clear();
		return null;
	}
}
