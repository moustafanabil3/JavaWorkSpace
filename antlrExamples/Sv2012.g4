
grammar Sv2012;

@header {
	package com.mos.grammar.sv;
}

@package {com.mos.grammar.sv}


module_declaration
	: module_nonansi_header ( module_item )* 'endmodule' ( ':' module_identifier )?
	| module_ansi_header  ( non_port_module_item )* 'endmodule' ( ':' module_identifier )?
//	| module_keyword ( lifetime )? module_identifier '(' '.' '*' ')' ';' ( module_item )*  'endmodule' ( ':' module_identifier )?
//	| 'extern' module_nonansi_header
//	| 'extern' module_ansi_header
	;
	
module_nonansi_header
	:  (attribute_instance)* module_keyword (lifetime)? module_identifier
       (package_import_declaration) [ parameter_port_list ] list_of_ports ;

package_import_declaration :
		'import' package_import_item  ( ',' package_import_item )*  ';'
	;

package_import_item :
		package_identifier ':' ':' identifier
	|	package_identifier ':' ':' '*'
	;
	
package_identifier	: identifier;

module_keyword	: 'module';
lifetime 		: 'static' | 'automatic';
      
module_identifier	: identifier;

identifier:	SIMPLE_IDENTIFIER;

SIMPLE_IDENTIFIER: [a-zA-Z_] ([a-zA-Z0-9_$])* ;


WS  :   [ \t]+ -> skip ; // toss out whitespace.

