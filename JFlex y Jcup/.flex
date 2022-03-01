/* comienzo del codigo del usuario*/

package analizador;



/*final del codigo del usuario */

/*inicio de la configuracion*/
%%

%class Analizador
%unicode
%line
%column
%cup
%public

palabra = [\"][[a-z]|[A-Z]|[0-9]|[" "]]+[\"] //palabras que pueden ir en comentarios o en titulos
decimal = [0-9]+[.][0-9]+
comentario = [#][[a-z]|[A-Z]|[0-9]|[" "]]*[\n]*
entero = [0-9]+
Barras = "Barras"
Pie = "Pie"
titulo = "titulo"
ejeX = "ejex"
ejeY = "ejey"
etiquetas = "etiquetas"
Ejecutar = "Ejecutar"
valores = "valores"
unir = "unir"
tipo = "tipo"
total = "total"
extra = "extra"
Cantidad = "Cantidad"
Porcentaje = "Porcentaje"
espacioBlanco = " "
tab = "\t"
enter = "\n"
%% 

//PALABRAS RESERVADAS

//para la parabra reservada def
[D|d][e][f]  {return new Symbol(sym.DEF, yyline + 1, yycolumn + 1, yytext());}
//palabra reservada barras
{Barras} {return new Symbol(sym.BARRAS, yyline + 1, yycolumn + 1, yytext());}
//palabra reservada pie
{Pie} {return new Symbol(sym.PIE, yyline + 1, yycolumn + 1, yytext());}
//palabra reservada titulo
{titulo} {return new Symbol(sym.TITULO, yyline + 1, yycolumn + 1, yytext());}
//palabra reservada eje
{ejeX} {return new Symbol(sym.EJEX, yyline + 1, yycolumn + 1, yytext());}
{ejeY} {return new Symbol(sym.EJEY, yyline + 1, yycolumn + 1, yytext());}
//palabra reservada etiqueta
{etiquetas} {return new Symbol(sym.ETIQUETA, yyline + 1, yycolumn + 1, yytext());}
{Ejecutar} {return new Symbol(sym.EJECUTAR, yyline + 1, yycolumn + 1, yytext());}
//token parentesis izquierdo
//palabra reservada valores
{valores} {return new Symbol(sym.VALORES, yyline + 1, yycolumn + 1, yytext());}
//palabra reservada unir
{unir} {return new Symbol(sym.UNIR, yyline + 1, yycolumn + 1, yytext());}
//palara tipo
{tipo} {return new Symbol(sym.TIPO, yyline + 1, yycolumn + 1, yytext());}
//palabra total
{total} {return new Symbol(sym.TOTAL, yyline + 1, yycolumn + 1, yytext());}
//palabra extra
{extra} {return new Symbol(sym.EXTRA, yyline + 1, yycolumn + 1, yytext());}
{Cantidad} {return new Symbol(sym.CANTIDAD, yyline + 1, yycolumn + 1, yytext());}
{Porcentaje} {return new Symbol(sym.PORCENTAJE, yyline + 1, yycolumn + 1, yytext());}

//TOKENS DE LOS SIMBOLOS UTILIZADOS EN EL LENGUAJE

["+"] {return new Symbol(sym.MAS, yyline + 1, yycolumn + 1, yytext());}//mas
["-"] {return new Symbol(sym.MENOS, yyline + 1, yycolumn + 1, yytext());}//menos
["*"] {return new Symbol(sym.POR, yyline + 1, yycolumn + 1, yytext());}//por
["/"] {return new Symbol(sym.DIAGONAL, yyline + 1, yycolumn + 1, yytext());}//
["("] {return new Symbol(sym.PARENTESISIZQUIERDO, yyline + 1, yycolumn + 1, yytext());}//parentesis izquierdo
[")"] {return new Symbol(sym.PARENTESISDERECHO, yyline + 1, yycolumn + 1, yytext());}//parentesis derecho
[";"] {return new Symbol(sym.PUNTOYCOMA, yyline + 1, yycolumn + 1, yytext());}//punto y coma
[","] {return new Symbol(sym.COMA, yyline + 1, yycolumn + 1);}//coma
["["] {return new Symbol(sym.CORCHETEIZQUIERDO, yyline + 1, yycolumn + 1, yytext());}//corchete izquierdo
["]"] {return new Symbol(sym.CORCHETEDERECHO, yyline + 1, yycolumn + 1, yytext());}//corchete derecho
["{"] {return new Symbol(sym.LLAVEIZQUIERDA, yyline + 1, yycolumn + 1, yytext());}//llave izquierda
["}"] {return new Symbol(sym.LLAVEDERECHA, yyline + 1, yycolumn + 1, yytext());}//llave derecha
[":"] {return new Symbol(sym.DOSPUNTOS, yyline + 1, yycolumn + 1, yytext());}//dos puntos
{espacioBlanco} {} //espacio en blanco
{enter} {} //salto de linea
{tab} {}//tabulador
//TOKENS COMPUESTOS

{comentario} {}//comentarios
{entero} {return new Symbol(sym.ENTERO, yyline + 1, yycolumn + 1, Double.parseDouble(yytext()));}//enteros
{decimal} {return new Symbol(sym.DECIMAL, yyline + 1, yycolumn + 1, Double.parseDouble(yytext()));}//decimales
{palabra} {return new Symbol(sym.PALABRA, yyline + 1, yycolumn + 1, yytext());}//palabra que tendra el titulo

[^] {
errores.add(new ErrorDeEntrada(yytext(), yyline + 1, yycolumn + 1, "Lexico","Símbolo no existe en el lenguaje"));

}//error
