package model.actores

data class Empregado(
    var id: Int = 0,
    val salario: Float,
    val jobArea: UserType,
    var idUser: Int,
    var user: User? = null
) {
}
