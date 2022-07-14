package `object`

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Account {
//    private val codigo= mutableStateOf("")
    private var codigo by mutableStateOf("")
    private var password by mutableStateOf("")
    var codigoValidationMessage by mutableStateOf(MessageValidation())
    var passwordValidationMessage by mutableStateOf(MessageValidation())
    fun addCodigo(codigo:String){
        this.codigo=codigo
        validateCodigo()
    }
    fun addPassword(password:String){
        this.password=password

    }
    private fun validateCodigo(){
        codigoValidationMessage = if(this.codigo.isEmpty()){
            MessageValidation()
        }else{
            if(this.codigo.isBlank()){
                codigoValidationMessage.copy(mensagem = "Erro1", status = false)
            }else if( this.codigo == "0000"){
                MessageValidation()
            }else{
                codigoValidationMessage.copy(mensagem = "Erro3", status = false)
            }
        }
    }
    fun login(){
        validatePassword()

    }
    private fun validatePassword(){
        passwordValidationMessage=if(password.isEmpty()){
            MessageValidation()
        }else{
            if(password.isBlank()){
                passwordValidationMessage.copy(mensagem="Erro1",status=false)
            }else if(this.password=="1234"){
                MessageValidation()
            }else{
                passwordValidationMessage.copy(mensagem = "Erro2", status = false)
            }
        }

    }



}
data class MessageValidation(var mensagem: String="Sucesso", var status:Boolean=true)