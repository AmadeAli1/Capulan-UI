package service

import model.actores.Cliente
import model.actores.Funcionario
import model.actores.formulario.ClienteBody
import model.actores.formulario.EmpregadoBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("user/cliente")
    suspend fun register(@Body clienteBody: ClienteBody, @Query("location") location: Int): Response<Cliente>

    @POST("user/empregado")
    suspend fun register(@Body empregadoBody: EmpregadoBody, @Query("location") location: Int?): Response<Funcionario>

    @GET("user/login/work")
    suspend fun login(@Query("codigo") codigo: Int, @Query("senha") senha: String): Response<Funcionario>

    @GET("user/cliente")
    suspend fun findAllClients(): Response<List<Cliente>>

    @GET("user/empregado")
    suspend fun findAllEmpregados(): Response<List<Funcionario>>

    @DELETE("user/delete")
    suspend fun delete(@Query("id") idUser: Int): Response<Boolean>

}