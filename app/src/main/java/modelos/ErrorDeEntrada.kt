package modelos

class ErrorDeEntrada(lexema: String, linea: Int, columna: Int, tipo: String, descripcion: String) {

    private val lexema: String = lexema

    private val linea: Int = linea

    private val columna: Int = columna

    private val descripcion: String = descripcion

    private val tipo: String = tipo

    fun getLexema(): String{
        return lexema
    }
    fun getLinea(): Int{
        return linea
    }

    fun getColumna(): Int{
        return columna
    }

    fun getDescripcion(): String{
        return descripcion
    }

    fun getTipo(): String{
        return tipo
    }

}