package creadoresDeGraficas

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import modelos.GraficaBarra

class CreadorGraficaBarra(private val layout: LinearLayout, private val context: Context) {

    fun crearGraficaDeBarra(graficaObjeto: GraficaBarra) {
        var barra = BarChart(context);
        barra.minimumHeight = 400
        barra.setFitBars(true)
        barra = crearGrafica(graficaObjeto.getTitulo(), barra, graficaObjeto)
        barra.setDrawBarShadow(true)
        barra.setDrawGridBackground(true)
        barra.legend.isEnabled = false
        etiquetasEjeX(barra.xAxis, graficaObjeto)
        barra.data = getBarData(graficaObjeto)
        layout.addView(barra)
    }

    private fun crearGrafica(
        titulo: String,
        barChart: BarChart,
        graficaObjeto: GraficaBarra
    ): BarChart {
        barChart.description.text = titulo
        barChart.description.textSize = 15f
        barChart.animateY(2000)
        barChart.setBackgroundColor(Color.YELLOW)
        crearLeyendasDeLaGrafica(barChart, graficaObjeto)
        return barChart;
    }

    private fun crearLeyendasDeLaGrafica(barChart: BarChart, graficaObjeto: GraficaBarra) {
        val leyenda: Legend = barChart.legend//traemos la propiedad leyenda de la grafica
        leyenda.form = Legend.LegendForm.CIRCLE//modificamos su forma
        leyenda.horizontalAlignment =
            Legend.LegendHorizontalAlignment.CENTER//alienamos las leyendas al centro
        val leyendas: ArrayList<LegendEntry> =
            ArrayList()//creamos un array de las leyendas que tendra la grafica
        for (item in graficaObjeto.getEjeX()) {//iteramos por cada String en el eje x del objeto creado en el sintactico
            val leyendaNueva = LegendEntry()//crear una nueva leyenda
            leyendaNueva.label = item//la label de la leyenda igualar la al string
            leyendas.add(leyendaNueva)//agregar la nueva leyenda
        }
        leyenda.setCustom(leyendas)//agregamos a la propiedad legend de la grafica las leyendas recaudadas
    }

    private fun agregarBarras(graficaObjeto: GraficaBarra): ArrayList<BarEntry> {
        val barras: ArrayList<BarEntry> = ArrayList()
        for (item in graficaObjeto.getUnir()) {
            val ejeX = item.getEjeX()
            val ejeY = item.getEjeY()
            val valorEjeY = graficaObjeto.getEjeY().get(ejeY)
            barras.add(BarEntry(ejeX.toFloat(), valorEjeY.toFloat()))
        }
        return barras
    }

    private fun etiquetasEjeX(ejeX: XAxis, graficaObjeto: GraficaBarra) {
        ejeX.isGranularityEnabled = true
        ejeX.position = XAxis.XAxisPosition.BOTTOM
        ejeX.valueFormatter = IndexAxisValueFormatter(graficaObjeto.getEjeX())
    }


    private fun getData(dataSet: BarDataSet): BarDataSet {
        val colores = arrayOf(Color.BLUE, Color.RED, Color.YELLOW, Color.CYAN, Color.LTGRAY)
        dataSet.colors = colores.toMutableList()
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 20f
        return dataSet
    }

    private fun getBarData(graficaObjeto: GraficaBarra): BarData {
        var barDataSet: BarDataSet = getData(BarDataSet(agregarBarras(graficaObjeto), ""))
        barDataSet.barShadowColor = Color.GRAY
        var barData = BarData(barDataSet)
        barData.barWidth = 0.45f
        return barData
    }
}