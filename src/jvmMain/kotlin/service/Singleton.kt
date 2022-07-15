package service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Singleton {
    val SERVIDOR: Retrofit

    init {
        val retrofit = Retrofit.Builder().baseUrl("http://localhost:8080/api/mz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        SERVIDOR = retrofit
    }

}