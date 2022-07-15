package repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import service.Singleton.SERVIDOR
import service.UserService

object UserRepository {
    private val service: UserService = SERVIDOR.create(UserService::class.java)
    var currentUser by mutableStateOf<Any?>(null)
        private set

    fun currentUser(user: Any) {
        currentUser = user
    }

    init {
//        CoroutineScope(Dispatchers.IO).launch {
//            val user = User(
//                nome = "Funcionario",
//                userType = UserType.FUNCIONARIO,
//                bi = "12345678021f3",
//                senha = "amade13",
//                sexo = Genre.MASCULINO,
//                idTerminal = 1
//            )
//            val emp = Empregado(salario = 82812f, idUser = 0, jobArea = JobArea.BALCONISTA)
//            val body = EmpregadoBody(user = user, empregado = emp)
//
//            val sh = service.register(body)
//            if (sh.isSuccessful) {
//                println(sh.body())
//            } else {
//                println(sh.message())
//            }
//
//        }
    }

    suspend fun login(codigo: Int, senha: String) = service.login(codigo, senha)
}