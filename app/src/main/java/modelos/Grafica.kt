package modelos

open class Grafica(private var titulo: String, private var unir: ArrayList<Unir>) {

    fun getTitulo(): String {
        return titulo
    }

    fun getUnir(): ArrayList<Unir> {
        return unir
    }
}