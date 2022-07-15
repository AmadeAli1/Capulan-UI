package model.actores

data class Cliente(
    val id: Int = 0,
    var email: String,
    var codigoPostal: String,
    var cidade: String,
    var idUser: Int,
    var user: User
)