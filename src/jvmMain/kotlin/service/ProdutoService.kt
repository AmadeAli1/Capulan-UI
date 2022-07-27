package service

import model.produto.Encomenda
import model.produto.Produto
import retrofit2.Response
import retrofit2.http.GET

interface ProdutoService {

    @GET("produto/encomenda")
    suspend fun findAllEncomenda(): Response<List<Encomenda>>

    @GET("produto")
    suspend fun findAllProdutos(): Response<List<Produto>>

}