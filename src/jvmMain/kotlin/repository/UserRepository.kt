package repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import model.actores.Cliente
import model.actores.Empregado
import service.Singleton.SERVIDOR
import service.UserService

object UserRepository {
    private val service: UserService = SERVIDOR.create(UserService::class.java)
    var currentUser by mutableStateOf<Empregado?>(null)
        private set

    fun currentUser(user: Empregado) {
        currentUser = user
    }


    suspend fun login(codigo: Int, senha: String) = service.login(codigo, senha)

    suspend fun findAllClientes(): List<Cliente> {
        val allClients = service.findAllClients()
        if (allClients.isSuccessful) {
            return allClients.body()!!
        }
        return emptyList()
    }
}