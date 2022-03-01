package com.example.graficas

import analizadores.analizadorLexico.Analizador
import analizadores.analizadorSintactico.ParserPrimeraPractica
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.snackbar.Snackbar
import creadoresDeGraficas.CreadorGraficaBarra
import creadoresDeGraficas.CreadorGraficaPie
import kotlinx.android.synthetic.main.activity_main.*
import modelos.GraficaBarra
import modelos.GraficaPie
import reportes.ReporteDeErrores
import reportes.ReporteOcurrenciasMatematicas
import java.io.Reader
import java.io.StringReader


class MainActivity : AppCompatActivity() {

    private lateinit var layout: LinearLayout
    private lateinit var txtReporteDeErrores: EditText
    private lateinit var txtReporteRecuentoGraficas: EditText
    private lateinit var txtReporteOcurrencias: EditText
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layout = pantalla2 //inicializar el layout
        btn = btnCompilar
        txtReporteDeErrores = txtReporteErrores
        txtReporteRecuentoGraficas = txtReporteGraficosDefinidos
        txtReporteOcurrencias = txtReporteOcurrenciasMatematicas
        btn.setOnClickListener {//agregamos el metodo del boton
            comenzarAnalisis()
        }

    }

    fun comenzarAnalisis() {
        //reseteamos los textos de los reportes
        txtReporteDeErrores.setText("")
        txtReporteRecuentoGraficas.setText("")
        txtReporteOcurrencias.setText("")
        layout.removeAllViews()//quitamos todas las graficas que esten en pantalla
        //prepraramos el analizador lexico y sintactico para el analisis del texto
        val entrada = txtEntrada.text.toString()
        val reader: Reader = StringReader(entrada)
        val analizador = Analizador(reader)
        val analizadorSintactico = ParserPrimeraPractica(analizador);
        //creamos los creadodes de las graficas
        val creadorGraficaBarra = CreadorGraficaBarra(layout, this)
        val creadorGraficaPie = CreadorGraficaPie(layout, this);
        try {
            analizadorSintactico.parse()//analizamos el texto con el analizador sintactico
            //buscamos dentro del array de ejecucion y encontrar la grafica a ejecutar
            if (analizador.errores.isEmpty() && analizadorSintactico.errores.isEmpty()) {
                for (item in analizadorSintactico.ejecutar) {
                    //por cada iteracion de los ejecutar vamos buscando el titulo en el array de graficas creadas
                    for (item2 in analizadorSintactico.graficas) {
                        if (item.equals(item2.getTitulo())) {//si el titulo coincide entonces vemos que clase de grafica es
                            if (item2 is GraficaBarra) {//vemos si se trata de una grafica de barra
                                creadorGraficaBarra.crearGraficaDeBarra(item2)
                            } else if (item2 is GraficaPie) {//de no serlo entonces ejecutamos la creacion de una grafica de pie
                                creadorGraficaPie.crearGraficaDePie(item2)
                            }
                        }
                    }
                }//una vez ejecutadas las graficas podemos lanzar los reportes de recuentos
                txtReporteRecuentoGraficas.setText(
                    "Graficas de Pie: " +
                            analizadorSintactico.totalPie + " Graficas de Barras: " +
                            analizadorSintactico.totalBarra
                )//no se necesita una clase estra para crear una linea de texto por lo tanto desde aqui seteamos el reporte de graficas definidas
                val reporteOcurrenciasMatematicas = ReporteOcurrenciasMatematicas(
                    txtReporteOcurrencias,
                    analizadorSintactico.ocurrencias
                )
                reporteOcurrenciasMatematicas.crearReporteDeOcurrencias()
            } else {//si hay errores entonces mandamos a crear el reporte
                val reporteDeErrores =
                    ReporteDeErrores(
                        txtReporteDeErrores,
                        analizador.errores,
                        analizadorSintactico.errores
                    )
                reporteDeErrores.crearReporteDeErrores()
            }

        } catch (e: Exception) {
            Snackbar.make(
                findViewById(R.id.pantalla),
                e.stackTraceToString(),
                2000
            ).show()
        }
    }
}