package model.actores

data class Cliente(
    var idUser: Int,
    val nome: String,
    val bi: String,
    val userType: UserType,
    val senha: String,
    val sexo: Genre,
    val idTerminal: Int,
    val email: String,
    var id: Int = 0,
    var codigoPostal: String,
    var cidade: String,
)