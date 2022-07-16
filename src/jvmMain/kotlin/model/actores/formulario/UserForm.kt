package model.actores.formulario

import model.actores.Genre
import model.actores.UserType

data class UserForm(
    var id: Int = 0,
    val nome: String, val bi: String,
    val userType: UserType,
    val senha: String,
    val sexo: Genre,
    val idTerminal: Int
)