package repository

import model.produto.Encomenda
import service.ProdutoService
import service.Singleton

class ProdutoRepository(
    private val servise: ProdutoService = Singleton.SERVIDOR.create(ProdutoService::class.java),
) {

    suspend fun findAll(): List<Encomenda> {
        val response = servise.findAll()
        return if (response.isSuccessful) response.body()!! else emptyList()
    }


}