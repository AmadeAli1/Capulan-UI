package service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection

object Singleton {
    val SERVIDOR: Retrofit

    init {
        val retrofit = Retrofit.Builder().baseUrl("http://localhost:8080/api/mz/")
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