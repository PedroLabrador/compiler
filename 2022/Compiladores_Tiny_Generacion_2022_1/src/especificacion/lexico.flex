package ve.edu.unet;

import java_cup.runtime.*;
import java.io.Reader;
//import otros.*;

%%
/* Habilitar la compatibilidad con el interfaz CUP para el generador sintactico*/
%cup
/* Llamar Scanner a la clase que contiene el analizador Lexico */
%class Lexico

/*-- DECLARACIONES --*/
%{
	public Lexico(Reader r, SymbolFactory sf){
        this(r);
		this.sf=sf;
		lineanum=0;
		debug=true;
	}
	private SymbolFactory sf;
	private int lineanum;
	private boolean debug;


/******************************************************************
BORRAR SI NO SE NECESITA
	//TODO: Cambiar la SF por esto o ver que se hace
	//Crear un nuevo objeto java_cup.runtime.Symbol con informaci�n sobre el token actual sin valor
 	  private Symbol symbol(int type){
    		return new Symbol(type,yyline,yycolumn);
	  }
	//Crear un nuevo objeto java_cup.runtime.Symbol con informaci�n sobre el token actual con valor
	  private Symbol symbol(int type,Object value){
    		return new Symbol(type,yyline,yycolumn,value);
	  }
******************************************************************/
%}
%eofval{
    return sf.newSymbol("EOF",sym.EOF);
%eofval}

/* Acceso a la columna y fila actual de analisis CUP */
%line
%column



digito		= [0-9]
numero		= {digito}+
letra			= [a-zA-Z]
identificador	= {letra}+
nuevalinea		= \n | \n\r | \r\n
espacio		= [ \t]+
%%
"if"            {	if(debug) System.out.println("token IF");
			return sf.newSymbol("IF",sym.IF);
			}
"then"          { if(debug) System.out.println("token THEN");
			return sf.newSymbol("THEN",sym.THEN);
			}
"else"          {	if(debug) System.out.println("token ELSE");
			return sf.newSymbol("ELSE",sym.ELSE);
			}
"end"           {	if(debug) System.out.println("token END");
			return sf.newSymbol("END",sym.END);
			}
"repeat"        {	if(debug) System.out.println("token REPEAT");
			return sf.newSymbol("REPEAT",sym.REPEAT);
			}
"until"         {	if(debug) System.out.println("token UNTIL");
			return sf.newSymbol("UNTIL",sym.UNTIL);
			}
"for"       {	if(debug) System.out.println("token FOR");
				return sf.newSymbol("FOR",sym.FOR);
			}
"while"     {	if(debug) System.out.println("token WHILE");
				return sf.newSymbol("WHILE",sym.WHILE);
			}
"read"          {	if(debug) System.out.println("token READ");
			return sf.newSymbol("READ",sym.READ);
			}
"write"         {	if(debug) System.out.println("token WRITE");
			return sf.newSymbol("WRITE",sym.WRITE);
			}
":="            {	if(debug) System.out.println("token ASSIGN");
			return sf.newSymbol("ASSIGN",sym.ASSIGN);
			}
"="             {	if(debug) System.out.println("token EQ");
			return sf.newSymbol("EQ",sym.EQ);
			}
"<>"             {	if(debug) System.out.println("token NEQ");
			return sf.newSymbol("NEQ",sym.NEQ);
}
"<"             {	if(debug) System.out.println("token LT");
			return sf.newSymbol("LT",sym.LT);
			}
">"             {	if(debug) System.out.println("token GT");
			return sf.newSymbol("GT",sym.GT);
			}
">="            {	if(debug) System.out.println("token GTEQ");
			return sf.newSymbol("GTEQ",sym.GTEQ);
			}		
"<="            {	if(debug) System.out.println("token LTEQ");
			return sf.newSymbol("LTEQ",sym.LTEQ);
			}			
"+"             {	if(debug) System.out.println("token PLUS");
			return sf.newSymbol("PLUS",sym.PLUS);
			}
"-"             {	if(debug) System.out.println("token MINUS");
			return sf.newSymbol("MINUS",sym.MINUS);
			}
"*"             {	if(debug) System.out.println("token TIMES");
			return sf.newSymbol("TIMES",sym.TIMES);
			}
"/"         {	if(debug) System.out.println("token OVER");
				return sf.newSymbol("OVER",sym.OVER);
			}
"mod"       {	if(debug) System.out.println("token MOD");
				return sf.newSymbol("MOD",sym.MOD);
			}
"and"       {	if(debug) System.out.println("token AND");
				return sf.newSymbol("AND",sym.AND);
			}
"or"        {	if(debug) System.out.println("token OR");
				return sf.newSymbol("OR",sym.OR);
			}
"not"       {	if(debug) System.out.println("token NOT");
				return sf.newSymbol("NOT",sym.NOT);
			}			
"("         {	if(debug) System.out.println("token LPAREN");
				return sf.newSymbol("LPAREN",sym.LPAREN);
			}
")"         {	if(debug) System.out.println("token RPAREN");
				return sf.newSymbol("RPAREN",sym.RPAREN);
			}
";"         {	if(debug) System.out.println("token SEMI");
				return sf.newSymbol("SEMI",sym.SEMI);
			}
","         {	if(debug) System.out.println("token COLON");
				return sf.newSymbol("SEMI",sym.COLON);
			}
"do"        {	if(debug) System.out.println("token DO");
				return sf.newSymbol("DO",sym.DO);
}
"bool"      {	if(debug) System.out.println("token BOOL");
           		return sf.newSymbol("BOOL",sym.BOOL);
			}
"int"       {	if(debug) System.out.println("token INT");
				return sf.newSymbol("INT",sym.INT);
            }
{numero}    {	if(debug) System.out.println("token NUM");
				return sf.newSymbol("NUM",sym.NUM,new String(yytext()));
			}
{identificador}	{	if(debug) System.out.println("token ID");
				return sf.newSymbol("ID",sym.ID,new String(yytext()));
			}
{nuevalinea}       {lineanum++;}
{espacio}    { /* saltos espacios en blanco*/}
"{"[^}]+"}"  { /* salto comentarios */ if(debug) System.out.println("token COMENTARIO"); }
.               {System.err.println("Caracter Ilegal encontrado en analisis lexico: " + yytext() + "\n");}