package creadoresDeGraficas

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import modelos.GraficaBarra
import modelos.GraficaPie

class CreadorGraficaPie(private val layout: LinearLayout, private val context: Context) {
    private var colores : ArrayList<Int> = ArrayList()

    fun crearGraficaDePie(graficaObjeto: GraficaPie) {
        llenarColores(graficaObjeto)
        var graficaPie = PieChart(context)
        graficaPie.minimumHeight = 400
        graficaPie = personalizarGrafica(
            graficaObjeto.getTitulo(),
            graficaPie,
            graficaObjeto
        )//mandamos a personalizar la grafica
        graficaPie.data = getPieData(graficaObjeto)
        layout.addView(graficaPie)
    }

    private fun crearLeyendasDeLaGrafica(pieChart: PieChart, graficaObjeto: GraficaPie) {
        val leyenda: Legend = pieChart.legend//traemos la propiedad leyenda de la grafica
        leyenda.form = Legend.LegendForm.CIRCLE//modificamos su forma
        leyenda.horizontalAlignment =
            Legend.LegendHorizontalAlignment.CENTER//alienamos las leyendas al centro
        val leyendas: ArrayList<LegendEntry> =
            ArrayList()//creamos un array de las leyendas que tendra la grafica
        var contador = 0;
        for(item in graficaObjeto.getUnir()){
            val leyendaNueva = LegendEntry()//crear una nueva leyenda
            leyendaNueva.formColor = colores.get(contador)
            leyendaNueva.label = graficaObjeto.getEtiquetas().get(item.getEjeX())//la label de la leyenda igualarla al string
            leyendas.add(leyendaNueva)//agregar la nueva leyenda
            contador++
        }
        leyenda.setCustom(leyendas)//agregamos a la propiedad legend de la grafica las leyendas recaudadas
    }

    private fun personalizarGrafica(
        titulo: String,
        pieChart: PieChart,
        graficaObjeto: GraficaPie
    ): PieChart {
        pieChart.description.text = titulo
        pieChart.description.textSize = 15f
        pieChart.animateY(2000)
        pieChart.setBackgroundColor(Color.CYAN)
        crearLeyendasDeLaGrafica(pieChart, graficaObjeto)
        return pieChart;
    }

    private fun agregarPies(graficaObjeto: GraficaPie): ArrayList<PieEntry> {
        val entradas = ArrayList<PieEntry>()

        for(item in graficaObjeto.getUnir()){
            entradas.add(PieEntry(graficaObjeto.getValores().get(item.getEjeY()).toFloat()))
        }

        return entradas
    }

    private fun datosGenerales(pieDataSet: PieDataSet): PieDataSet {
        pieDataSet.colors = colores.toMutableList()
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.valueTextSize = 20f
        return pieDataSet
    }

    private fun getPieData(graficaObjeto: GraficaPie): PieData {
        var pieDataSet: PieDataSet = datosGenerales(PieDataSet(agregarPies(graficaObjeto), ""))
        pieDataSet.sliceSpace = 2f
        if (graficaObjeto.getTipo().equals("Porcentaje")) {
            pieDataSet.valueFormatter = PercentFormatter()
        }
        var pieData = PieData(pieDataSet)
        return pieData
    }

    private fun llenarColores(graficaObjeto: GraficaPie){
        var contador = 0;
        for(item in graficaObjeto.getEtiquetas()){
            contador += 200
            colores.add(Color.rgb(contador,0,0))
        }
    }

}