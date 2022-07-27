package repository

import model.produto.Categoria
import model.produto.Encomenda
import model.produto.Produto
import model.produto.Stock
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


    suspend fun findAllCategorias(): List<Categoria> {
        val response = servise.findAllCategoria()
        if (response.isSuccessful) {
            println("Sucesso CATEGORIA!")
            println(response.body()?.size)
            return response.body()!!
        } else {
            println(response.message())
        }
        return emptyList()
    }


    suspend fun findAllStocks(): List<Stock> {
        val response = servise.findAllStocks()
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