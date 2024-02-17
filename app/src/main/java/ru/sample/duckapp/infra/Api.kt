package ru.sample.duckapp.infra

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sample.duckapp.data.DucksApi
import ru.sample.duckapp.data.DuckUrlProvider
import ru.sample.duckapp.domain.DuckContainer

object Api : DuckUrlProvider {
    private const val BASE_URL = "https://random-d.uk/api/v2/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val ducksApi by lazy {
        retrofit.create(DucksApi::class.java)
    }

    override fun getDuckUrl(duckContainer: DuckContainer): String {
        return BASE_URL.plus("http/${duckContainer.code}")
    }
}