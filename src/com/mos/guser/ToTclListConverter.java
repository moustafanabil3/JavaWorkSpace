package com.mos.guser;

import com.mos.lang.arrayinit.ArrayInitBaseListener;
import com.mos.lang.arrayinit.ArrayInitParser.InitContext;
import com.mos.lang.arrayinit.ArrayInitParser.ValueContext;

public class ToTclListConverter extends ArrayInitBaseListener {
	
	private boolean isFirstValue = true;
	
	@Override
	public void enterInit(InitContext ctx) {
		System.out.print("[ ");
		this.isFirstValue = true;
	}
	
	@Override
	public void exitInit(InitContext ctx) {
		System.out.print("]" );
	}
	
	@Override
	public void enterValue(ValueContext ctx) {
		if (!this.isFirstValue) {
			System.out.print(", " );
		} else {
			this.isFirstValue = false;
		}
		
		// if it has a value print it, else, continue as it would be a new list
		if (ctx.INT() != null) {
			System.out.print(ctx.INT().getText());
		}
	}
}
