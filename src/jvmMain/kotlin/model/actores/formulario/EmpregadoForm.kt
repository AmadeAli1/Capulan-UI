package model.actores.formulario

import model.actores.UserType

data class EmpregadoForm(
    var id: Int = 0,
    var salario: Float? = null,
    var jobArea: UserType? = null,
    var idUser: Int? = null,
    var user: UserForm? = null
)
