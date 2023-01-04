package com.arwani.ahmad.schotersnews.data.network.retrofit

import com.arwani.ahmad.schotersnews.data.network.NetworkConstant
import com.arwani.ahmad.schotersnews.data.network.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(NetworkConstant.top_headlines)
    suspend fun getNews(
        @Query(NetworkConstant.country) country: String,
        @Query(NetworkConstant.category) category: String,
        @Query(NetworkConstant.api_key) apiKey: String
    ): NewsResponse
}