package screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import model.actores.formulario.ClienteForm
import model.actores.formulario.UserForm

class ClienteViewModel {
    private var cliente by mutableStateOf(ClienteForm())
    private var user by mutableStateOf(UserForm())
    private val validate = Validate()

    private fun validate(): Boolean {
        return validate.validate(cliente.cidade)
            .validate(cliente.codigoPostal)
            .validate(cliente.email)
            .validate(user.bi)
            .validate(user.userType)
            .validate(user.senha)
            .validate(user.nome)
            .validate(user.sexo)
            .validate(user.idTerminal)
            .isValid
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