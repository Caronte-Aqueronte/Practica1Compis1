package reportes

import android.content.Context
import android.text.Editable
import android.widget.*
import modelos.ErrorDeEntrada


class ReporteDeErrores(
    txtErrores: EditText,
    erroresLexicos: ArrayList<ErrorDeEntrada>,
    erroresSintacticos: ArrayList<ErrorDeEntrada>
) {

    private val txtErrores: EditText = txtErrores
    private val erroresLexicos: ArrayList<ErrorDeEntrada> = erroresLexicos
    private val erroresSintacticos: ArrayList<ErrorDeEntrada> = erroresSintacticos


    fun crearReporteDeErrores() {
        var listadoDeErrores = ""
        for (x: Int in 0 until erroresLexicos.size - 1) {
            listadoDeErrores += "Lexema: " + erroresLexicos.get(x)
                .getLexema() + " Fila: " + erroresLexicos.get(x)
                .getLinea() + " Columna: " + erroresLexicos.get(x)
                .getColumna() + " Tipo: " + erroresLexicos.get(x)
                .getTipo() + " Descripción: " + erroresLexicos.get(x).getDescripcion() + "\n"
        }
        var contadorErroresSintacticos = 0
        for (item in erroresSintacticos) {
            listadoDeErrores += "Lexema: " + erroresSintacticos.get(contadorErroresSintacticos)
                .getLexema() + " Fila: " + erroresSintacticos.get(contadorErroresSintacticos)
                .getLinea() + " Columna: " + erroresSintacticos.get(contadorErroresSintacticos)
                .getColumna() + " Tipo: " + erroresSintacticos.get(contadorErroresSintacticos)
                .getTipo() + " Descripción: " + erroresSintacticos.get(contadorErroresSintacticos)
                .getDescripcion() + "\n"
            contadorErroresSintacticos++
        }

        txtErrores.setText(listadoDeErrores)
    }


}