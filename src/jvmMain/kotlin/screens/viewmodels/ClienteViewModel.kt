package screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import model.actores.Genre
import model.actores.UserType
import model.actores.formulario.ClienteBody
import model.actores.formulario.ClienteForm
import model.actores.formulario.UserForm
import repository.UserRepository
import service.Singleton
import service.UserService

class ClienteViewModel(
    private val service: UserService = Singleton.SERVIDOR.create(UserService::class.java)
) {
    private var cliente by mutableStateOf(ClienteForm())
    private var user by mutableStateOf(UserForm())
    private val validate by mutableStateOf(Validate())

    init {
        user.userType = UserType.CLIENTE
        user.idTerminal = UserRepository.currentUser!!.idTerminal
//        cliente = ClienteForm(email = "aliamadeoiu@gmail.com", codigoPostal = "postal111", cidade = "Maputo")
//        user = UserForm(
//            nome = "Jose Marques",
//            bi = "1l34567890123",
//            senha = "jose132",
//            userType = UserType.CLIENTE,
//            sexo = Genre.MASCULINO,
//            idTerminal = 1
//        )
//
//        runBlocking {
//            save()
//        }

    }

    private fun validate(): Boolean {
        return validate.validate(cliente.cidade)
            .validate(cliente.codigoPostal)
            .validate(cliente.email)
            .validate(user.bi)
            .validate(user.senha)
            .validate(user.nome)
            .validate(user.sexo)
            .isValid
    }

    fun onChangeFirstname(firstname: String) {
        user.nome = firstname
    }

    fun onChangeLastname(lastname: String) {
        user.nome = user.nome.plus(" $lastname")
    }

    fun onChangeCity(cityname: String) {
        cliente.cidade = cityname
    }

    fun onChangePostalCode(postalcode: String) {
        cliente.codigoPostal = postalcode
    }

    fun onChangeEmail(email: String) {
        cliente.email = email
    }


    fun onChangeBi(bi: String) {
        user.bi = bi
    }

    fun onChangeSenha(senha: String) {
        user.senha = senha
    }

    suspend fun save() {
        if (validate()) {
            val response = service.register(clienteBody = ClienteBody(cliente = cliente, user = user))
            if (response.isSuccessful) {
                println("sucesso")
            } else {
                println("Falha do Servidor:: ${response.errorBody()}")
            }
        } else {
            println("Dados invalidos")
        }
    }

    fun onChangeSexo(genre: Genre) {
        user.sexo = genre
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