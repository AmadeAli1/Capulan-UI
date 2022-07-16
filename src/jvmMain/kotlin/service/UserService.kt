package service

import model.actores.formulario.ClienteBody
import model.actores.formulario.EmpregadoBody
import model.actores.Cliente
import model.actores.Empregado
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {

    @POST("user/cliente")
    suspend fun register(@Body clienteBody: ClienteBody): Response<Cliente>

    @POST("user/empregado")
    suspend fun register(@Body empregadoBody: EmpregadoBody): Response<Empregado>

    @GET("user/login/work")
    suspend fun login(@Query("codigo") codigo: Int, @Query("senha") senha: String): Response<Empregado>

    @GET("user/cliente")
    suspend fun findAllClients(): Response<List<Cliente>>

    @GET("user/empregado")
    suspend fun findAllEmpregados(): Response<List<Empregado>>

}