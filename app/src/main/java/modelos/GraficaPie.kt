package modelos

class GraficaPie: Grafica{
    private var etiquetas: ArrayList<String>
    private var valores: ArrayList<Double>
    private var tipo : String
    private var total : Double
    private var extra : String

    constructor(
        titulo: String,
        unir: ArrayList<Unir>,
        etiquetas: ArrayList<String>,
        valores: ArrayList<Double>,
        tipo: String,
        total: Double,
        extra: String
    ) : super(titulo, unir) {
        this.etiquetas = etiquetas
        this.valores = valores
        this.tipo = tipo
        this.total = total
        this.extra = extra
    }

    fun getEtiquetas(): ArrayList<String>{
        return etiquetas
    }
    fun getValores(): ArrayList<Double>{
        return valores
    }
    fun getTipo(): String{
        return tipo
    }
    fun getTotal(): Double{
        return total
    }
    fun getExtra(): String{
        return extra
    }
}