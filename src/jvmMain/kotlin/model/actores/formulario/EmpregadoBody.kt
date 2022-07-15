package com.isctem.capulan.request

import model.actores.Empregado
import model.actores.User

data class EmpregadoBody(
    val user: User,
    val empregado: Empregado
)
