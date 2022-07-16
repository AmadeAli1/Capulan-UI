package model.actores.formulario

data class ClienteForm(
    val id: Int = 0,
    var email: String,
    var codigoPostal: String,
    var cidade: String,
    var idUser: Int,
    var user: UserForm
)
