package reportes

import android.widget.EditText
import modelos.OcurrenciaMatematica

class ReporteOcurrenciasMatematicas(
    txtOcurrenciasMatematicas: EditText,
    ocurrencias: ArrayList<OcurrenciaMatematica>
) {
    private val txtOcurrenciasMatematicas = txtOcurrenciasMatematicas
    private val ocurrencias = ocurrencias

    fun crearReporteDeOcurrencias() {
        var listadoDeOcurrencias = ""//aqui guardaremos todas las ocurrencias
        var contadorOcurrencias = 0
        for (item in ocurrencias) {//iteramos y agregamos un nuevo texto al listado
            listadoDeOcurrencias += "OCURRENCIA\nOperador: " + ocurrencias.get(contadorOcurrencias)
                .getOperador() + " Fila: " + ocurrencias.get(contadorOcurrencias)
                .getFila() + " Columna: " + ocurrencias.get(contadorOcurrencias)
                .getColumna() + " Ocurrencia: " + ocurrencias.get(contadorOcurrencias)
                .getOcurrencia() + "\n"
            contadorOcurrencias++
        }
        txtOcurrenciasMatematicas.setText(listadoDeOcurrencias)//le damos el texto al txt
    }
}