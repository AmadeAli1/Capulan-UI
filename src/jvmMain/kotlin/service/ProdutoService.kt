package service

import model.produto.Encomenda
import retrofit2.Response
import retrofit2.http.GET

interface ProdutoService {

    @GET("encomenda")
    suspend fun findAll(): Response<List<Encomenda>>

}