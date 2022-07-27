package screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import model.produto.Produto
import model.produto.Stock
import model.produto.StockForm
import repository.UserRepository
import service.ProdutoService
import service.Singleton

class StockViewModel(
    private val service: ProdutoService = Singleton.SERVIDOR.create(ProdutoService::class.java),
) {
    private val stockForm by mutableStateOf(StockForm(stock = Stock(), produto = Produto()))
    private var categoria = -1;
    private var fornecedor = -1;


    fun onValidate(): Boolean {
        return Validate().validate(stockForm.stock!!.quantidade)
            .validate(stockForm.stock!!.fornecedor)
            .validate(stockForm.stock!!.preco)
            .validate(stockForm.produto!!.preco)
            .validate(stockForm.produto!!.nome)
            .isValid
    }

    fun onChangeCategoria(idCategoria: Int) {
        this.categoria = idCategoria
    }

    fun onChangeQuantity(quantity: Int) {
        stockForm.stock!!.quantidade = quantity
    }

    fun onChangePrice(price: Int) {
        stockForm.stock!!.preco = price.toLong()
    }

    fun onChangeFornecedor(idFornecedor: Int) {
        stockForm.stock!!.fornecedor = idFornecedor
        this.fornecedor = idFornecedor
    }

    fun onChangeProdutoPrice(produtoPrice: Long) {
        stockForm.produto!!.preco = produtoPrice
    }

    fun onChangeProdutoNome(produtoNome: String) {
        stockForm.produto!!.nome = produtoNome
    }

    suspend fun save(): Boolean {
        if (onValidate()) {
            val save = service.save(
                stock = stockForm,
                location = UserRepository.currentUser!!.idTerminal,
                categoria = categoria,
                fornecedor = fornecedor
            )
            if (save.isSuccessful) {
                return save.body()!!
            }
        } else {
            println(stockForm)
            println("Dados Invalidos")
        }
        return false
    }


}