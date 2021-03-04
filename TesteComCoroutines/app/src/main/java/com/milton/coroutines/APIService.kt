package com.milton.coroutines

import retrofit2.http.GET

interface APIService {

    @GET("facts/random")
    suspend fun fetchCatFact(): CatFact
}