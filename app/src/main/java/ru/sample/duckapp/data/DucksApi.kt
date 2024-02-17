package ru.sample.duckapp.data

import retrofit2.Call
import retrofit2.http.GET
import ru.sample.duckapp.domain.DuckPayload

interface DucksApi {
    @GET("random")
    fun getRandomDuck(): Call<DuckPayload>
}