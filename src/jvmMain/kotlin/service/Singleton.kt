package service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Connection
import java.sql.DriverManager
import java.time.Duration

object Singleton {
    lateinit var SERVIDOR: Retrofit

    init {
        for (location in Server.values()) {

            try {
                val conn: Connection? = DriverManager.getConnection(
                    location.host, location.username, location.password
                )
                if (conn != null) {
                    println("Connected to the database: $location!")
                    val retrofit = Retrofit.Builder().baseUrl(location.server)
                        .client(
                            OkHttpClient.Builder()
                                .callTimeout(Duration.ofMinutes(3L))
                                .connectTimeout(Duration.ofMinutes(3L))
                                .readTimeout(Duration.ofMinutes(3L)).build()
                        )
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    SERVIDOR = retrofit
                    break
                }
            } catch (e: Exception) {
                println(e.message)
                continue
            }
        }


    }

}