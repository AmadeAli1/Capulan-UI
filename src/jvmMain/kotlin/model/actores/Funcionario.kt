package model.actores

data class Funcionario(
    val nome: String,
    val bi: String,
    val userType: UserType,
    val senha: String,
    val sexo: Genre,
    val idTerminal: Int,
    val salario: Float,
    val jobArea: JobArea,
    var idUser: Int,
    var id: Int = 0
)
