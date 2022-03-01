package modelos

class GraficaBarra(
    private var ejeX: ArrayList<String>,
    private var ejeY: ArrayList<Double>,
    titulo: String,
    unir: ArrayList<Unir>
) :
    Grafica(titulo, unir) {
    fun getEjeX(): ArrayList<String>{
        return ejeX
    }
    fun getEjeY(): ArrayList<Double>{
        return ejeY
    }
}