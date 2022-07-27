package repository

import model.produto.Encomenda
import model.produto.Produto
import service.ProdutoService
import service.Singleton

class ProdutoRepository(
    private val servise: ProdutoService = Singleton.SERVIDOR.create(ProdutoService::class.java),
) {

    suspend fun findAllEncomendas(): List<Encomenda> {
        val response = servise.findAllEncomenda()
        if (response.isSuccessful) {
            println("Sucesso!")
            println(response.body()?.size)
            return response.body()!!
        } else {
            println(response.message())
        }
        return emptyList()
    }


    suspend fun findAllProdutos(): List<Produto> {
        val response = servise.findAllProdutos()
        if (response.isSuccessful) {
            println("Sucesso!")
            println(response.body()?.size)
            return response.body()!!
        } else {
            println(response.message())
        }
        return emptyList()
    }


}