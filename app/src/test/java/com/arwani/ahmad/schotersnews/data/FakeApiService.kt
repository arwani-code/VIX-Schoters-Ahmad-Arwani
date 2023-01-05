package com.arwani.ahmad.schotersnews.data

import com.arwani.ahmad.schotersnews.DataDummy
import com.arwani.ahmad.schotersnews.data.network.response.NewsResponse
import com.arwani.ahmad.schotersnews.data.network.retrofit.ApiService

class FakeApiService : ApiService {
    private val dummyResponse = DataDummy.generateDummyNewsResponse()
    override suspend fun getNews(country: String, category: String, apiKey: String): NewsResponse {
        return dummyResponse
    }
}