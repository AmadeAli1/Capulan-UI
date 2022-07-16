package model.actores.formulario

import model.actores.UserType

data class EmpregadoForm(
    var id: Int = 0,
    val salario: Float,
    val jobArea: UserType,
    var idUser: Int,
    var user: UserForm? = null
)
