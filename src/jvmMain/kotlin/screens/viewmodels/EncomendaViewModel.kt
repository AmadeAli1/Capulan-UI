package screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import model.produto.Encomenda
import repository.UserRepository
import service.ProdutoService
import service.Singleton

class EncomendaViewModel(
    private val service: ProdutoService = Singleton.SERVIDOR.create(ProdutoService::class.java),

    ) {
    private val encomenda by mutableStateOf(Encomenda())
    private var idUsuario: Int? = null
    private var idFuncionario: Int? = UserRepository.currentUser!!.idUser
    private var idTerminal: Int = UserRepository.currentUser!!.idTerminal
    private var idProduto: Int? = null


    private fun validate(): Boolean {
        return Validate()
            .validate(encomenda.preco)
            .validate(encomenda.quantidade)
            .validate(idUsuario)
            .validate(idProduto)
            .isValid
    }

    fun onChangePrice(price: Long) {
        encomenda.preco = price
    }

    fun onChangeQuantity(quantity: Long) {
        encomenda.quantidade = quantity
    }

    fun onChangeUsuario(usuario: Int) {
        idUsuario = usuario
    }

    fun onChangeProduto(produto: Int) {
        idProduto = produto
    }

    suspend fun save(): Boolean {
        if (validate()) {
            val save = service.save(
                encomenda = encomenda,
                idProduto = idProduto!!,
                idFuncionario = idFuncionario!!,
                idUsuario = idUsuario!!,
                idTerminal = idTerminal
            )
            return if (save.isSuccessful) {
                save.body()!!
            } else {
                false
            }
        } else {
            return false
        }

    }

}