package com.isctem.capulan.request

import model.actores.Cliente
import model.actores.User

data class ClienteBody(
    val cliente: Cliente,
    val user: User
)
