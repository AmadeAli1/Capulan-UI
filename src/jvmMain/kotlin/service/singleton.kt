package service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object singleton {

    val SERVIDOR = Retrofit.Builder().baseUrl("http://localhost:8080/api/mz/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}