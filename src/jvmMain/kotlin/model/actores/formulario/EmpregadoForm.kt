package model.actores.formulario

import model.actores.JobArea
import model.actores.UserType

data class EmpregadoForm(
    var id: Int = 0,
    var salario: Float? = null,
    var jobArea: JobArea? = null,
    var idUser: Int? = null,
    var user: UserForm? = null
)
