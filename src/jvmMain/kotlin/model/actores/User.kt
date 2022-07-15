package model.actores

data class User(
    var id: Int = 0,
    val nome: String, val bi: String,
    val userType: UserType,
    val senha: String,
    val sexo: Genre,
    val idTerminal: Int
) {
}
