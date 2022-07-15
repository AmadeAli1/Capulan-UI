package model.actores

data class Empregado(
    var id: Int = 0,
    val salario: Float,
    val jobArea: JobArea,
    var idUser: Int,
    var user: User? = null
) {
}
