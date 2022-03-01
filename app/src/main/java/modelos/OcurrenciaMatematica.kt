package modelos

class OcurrenciaMatematica(operador: String, fila: Int, columna: Int, ocurrencia: String) {

    private val operador = operador
    private val fila = fila
    private val columna = columna
    private val ocurrencia = ocurrencia

    fun getOperador(): String {
        return operador
    }

    fun getFila(): Int {
        return fila
    }

    fun getColumna(): Int {
        return columna
    }

    fun getOcurrencia(): String {
        return ocurrencia
    }
}