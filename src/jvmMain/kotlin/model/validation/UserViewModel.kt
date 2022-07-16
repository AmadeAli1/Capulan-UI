package model.validation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.delay
import repository.UserRepository
import service.Message

class UserViewModel(
    private val userRepository: UserRepository = UserRepository
) {
    private var codigo by mutableStateOf("")
    private var password by mutableStateOf("")
    var codigoValidationMessage by mutableStateOf(MessageValidation())
    var passwordValidationMessage by mutableStateOf(MessageValidation())

    var progress = mutableStateOf(Progress())

    fun addCodigo(codigo: String) {
        this.codigo = codigo
        validateCodigo()
    }

    fun addPassword(password: String) {
        this.password = password
        validatePassword()
    }

    private fun validateCodigo() {
        codigoValidationMessage = if (codigo.isEmpty()) {
            MessageValidation("Empty")
        } else if (codigo.isBlank()) {
            MessageValidation(mensagem = "Must not be blank", status = false)
        } else if (!codigo.contains(Regex("^[0-9]*$"))) {
            MessageValidation(mensagem = "Introduza Somente Numeros", status = false)
        } else {
            MessageValidation()
        }
    }

    suspend fun login() {
        progress.value = Progress(true)
        validateCodigo()
        validatePassword()
        delay(600)
        val codigoEmpty = codigoValidationMessage.mensagem != "Empty"
        if (codigoValidationMessage.status.and(passwordValidationMessage.status).and(codigoEmpty)) {
            resetValidation()
            val login = userRepository.login(codigo = codigo.toInt(), senha = password)
            if (login.isSuccessful) {
                userRepository.currentUser(login.body()!!)
                println(login.body())
                progress.value = Progress(status = false, result = true)
            } else {
                val mapper = jacksonObjectMapper()
                val error = login.errorBody()!!.string()
                try {
                    val message: Message = mapper.readValue(error, Message::class.java)
                    if (message.field == "codigo") {
                        codigoValidationMessage = MessageValidation(mensagem = message.message, status = false)
                    } else if (message.field == "senha") {
                        passwordValidationMessage = MessageValidation(mensagem = message.message, status = false)
                    }
                } catch (e: JsonParseException) {
                    println(e.message)
                } catch (e: Exception) {
                    println(e)
                }
                progress.value = Progress(status = false, result = false)
            }
        }
    }


    private fun resetValidation() {
        codigoValidationMessage = MessageValidation()
        passwordValidationMessage = MessageValidation()
    }

    private fun validatePassword() {
        passwordValidationMessage = if (password.isBlank()) {
            MessageValidation(mensagem = "Must not be blank", false)
        } else if (password.length < 6) {
            MessageValidation(
                mensagem = "Tamanho minimo 6",
                false
            )
        } else {
            MessageValidation()
        }
    }

    data class MessageValidation(var mensagem: String = "Sucesso", var status: Boolean = true)
    data class Progress(val status: Boolean = false, val result: Boolean = false)

}