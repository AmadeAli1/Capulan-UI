package service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import java.sql.DriverManager
import java.time.Duration

object Singleton {
    val SERVIDOR: Retrofit

    init {
        val retrofit = Retrofit.Builder().baseUrl(Server.MOZAMBIQUE.server)
            .client(
                OkHttpClient.Builder()
                    .callTimeout(Duration.ofMinutes(3L))
                    .connectTimeout(Duration.ofMinutes(3L))
                    .readTimeout(Duration.ofMinutes(3L)).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        SERVIDOR = retrofit
    }

    fun netIsAvailable(): Boolean {
        return try {
            val url = URL("http://localhost:8080")
            val conn: URLConnection = url.openConnection()
            conn.connect()
            conn.getInputStream().close()
            true
        } catch (e: MalformedURLException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            false
        }
    }

}