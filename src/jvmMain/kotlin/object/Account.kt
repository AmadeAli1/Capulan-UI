package `object`

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Account {
    private var codigo by mutableStateOf("")
    private var password by mutableStateOf("")
    var codigoValidationMessage by mutableStateOf(MessageValidation())
    var passwordValidationMessage by mutableStateOf(MessageValidation())

    fun addCodigo(codigo: String) {
        this.codigo = codigo
        validateCodigo()
    }

    fun addPassword(password: String) {
        this.password = password
    }

    private fun validateCodigo() {

    }

    fun login() {

    }

    private fun validatePassword() {

    }

    data class MessageValidation(var mensagem: String = "Sucesso", var status: Boolean = true)
}