package model.actores

data class Cliente(
    val idUser: Int,
    val nome: String,
    val bi: String,
    val userType: UserType,
    val senha: String,
    val sexo: Genre,
    val idTerminal: Int,
    val email: String,
    val id: Int = 0,
    val codigoPostal: String,
    val cidade: String,
)