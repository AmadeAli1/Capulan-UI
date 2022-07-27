package service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.DriverManager
import java.time.Duration

class ServerConnection {

    companion object {
        @Volatile
        private var INSTANCE: ServerConnection? = null

        @Volatile
        lateinit var SERVICE: Retrofit

        fun Instance(): ServerConnection? {
            if (INSTANCE == null) {
                INSTANCE = ServerConnection()
                for (location in Server.values()) {
                    try {
                        val connection =
                            DriverManager.getConnection(location.host, location.username, location.password)
                        if (connection != null) {
                            val retrofit = Retrofit.Builder().baseUrl(location.server)
                                .client(
                                    OkHttpClient.Builder()
                                        .callTimeout(Duration.ofMinutes(3L))
                                        .connectTimeout(Duration.ofMinutes(3L))
                                        .readTimeout(Duration.ofMinutes(3L)).build()
                                )
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                            SERVICE = retrofit
                            break
                        }
                    } catch (e: Exception) {
                        continue
                    }
                }
            }
            return INSTANCE
        }
    }
}