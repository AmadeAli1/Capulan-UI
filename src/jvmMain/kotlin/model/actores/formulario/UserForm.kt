package model.actores.formulario

import model.actores.Genre
import model.actores.UserType

data class UserForm(
    var id: Int = 0,
    var nome: String? = null,
    var bi: String? = null,
    var userType: UserType? = null,
    var senha: String? = null,
    var sexo: Genre? = null,
    var idTerminal: Int? = null
)