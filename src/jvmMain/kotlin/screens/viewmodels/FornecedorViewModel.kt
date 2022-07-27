package screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import model.produto.Fornecedor
import service.ProdutoService
import service.Singleton

class FornecedorViewModel(
    private val service: ProdutoService = Singleton.SERVIDOR.create(ProdutoService::class.java),
) {
    private val fornecedor by mutableStateOf(Fornecedor())

    private fun validate(): Boolean {
        return Validate()
            .validate(fornecedor.contacto)
            .validate(fornecedor.email)
            .validate(fornecedor.nomeEmpresa)
            .isValid
    }

    fun onChangeContacto(contacto: String) {
        fornecedor.contacto = contacto
    }

    fun onChangeEmail(email: String) {
        fornecedor.email = email
    }

    fun onChangeEmpresa(empresa: String) {
        fornecedor.nomeEmpresa = empresa
    }

    suspend fun save(): Boolean {

        if (validate()) {
            val save = service.save(fornecedor)
            return if (save.isSuccessful) {
                save.body()!!
            } else {
                false
            }
        } else {
            println("Dados Invalidos")
        }
        return false
    }
}