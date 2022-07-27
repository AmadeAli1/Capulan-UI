package service

import model.produto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProdutoService {

    @GET("produto/encomenda")
    suspend fun findAllEncomenda(): Response<List<Encomenda>>

    @GET("produto")
    suspend fun findAllProdutos(): Response<List<Produto>>

    @GET("produto/categoria")
    suspend fun findAllCategoria(): Response<List<Categoria>>

    @GET("produto/stock")
    suspend fun findAllStocks(): Response<List<Stock>>

    @POST("produto/save")
    suspend fun save(
        @Body stock: StockForm,
        @Query("location") location: Int,
        @Query("categoria") categoria: Int,
        @Query("fornecedor") fornecedor: Int,
    ): Response<Boolean>
}