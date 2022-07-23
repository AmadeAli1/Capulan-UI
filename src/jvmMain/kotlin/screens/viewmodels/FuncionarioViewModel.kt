package screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import model.actores.Genre
import model.actores.JobArea
import model.actores.UserType
import model.actores.formulario.EmpregadoBody
import model.actores.formulario.EmpregadoForm
import model.actores.formulario.UserForm
import repository.UserRepository
import service.Singleton
import service.UserService

class FuncionarioViewModel(
    private val service: UserService = Singleton.SERVIDOR.create(UserService::class.java),
) {
    private var funcionario by mutableStateOf(EmpregadoForm())
    private var user by mutableStateOf(UserForm())
    private val validate by mutableStateOf(Validate())

    init {
        user.userType = UserType.FUNCIONARIO
        user.idTerminal = UserRepository.currentUser!!.idTerminal
        user.sexo = Genre.MASCULINO
        funcionario.jobArea = JobArea.BALCONISTA
    }

    private fun validate(): Boolean {
        return validate
            .validate(funcionario.salario)
            .validate(user.bi)
            .validate(user.senha)
            .validate(user.nome)
            .validate(user.sexo)
            .isValid
    }

    fun onChangeFirstname(firstname: String) {
        user.nome = firstname
    }

    fun onChangeBi(bi: String) {
        user.bi = bi
    }

    fun onChangeSexo(sexo: Genre) {
        user.sexo = sexo
    }

    fun onChangeSenha(senha: String) {
        user.senha = senha
    }

    suspend fun save(): Boolean {
        println(funcionario)
        println(user)
        if (validate()) {
            val response = service.register(
                empregadoBody = EmpregadoBody(empregado = funcionario, user = user),
                location = user.idTerminal
            )
            return if (response.isSuccessful) {
                println("sucesso")
                true
            } else {
                println("Falha do Servidor:: ${response.errorBody()!!.string()}")
                false
            }
        } else {
            println("Dados invalidos")
        }
        return false
    }

    fun onChangeJobArea(jobArea: JobArea) {
        funcionario.jobArea = jobArea
    }

    fun onChangeSalario(it: String) {
        funcionario.salario = it.toFloat()
    }


    private class Validate {
        var isValid = true
        fun <T> validate(field: T?): Validate {
            if (isValid) {
                isValid = field != null
            }
            return this
        }
    }
}