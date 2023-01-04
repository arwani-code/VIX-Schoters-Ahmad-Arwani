package com.arwani.ahmad.schotersnews

import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity

object DataDummy {
    fun generateDummyNewsEntity(): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        for (i in 0..10) {
            val news = NewsEntity(
                id = i,
                title = "Diduga Tak Sejalan dengan Istri Indra Bekti, Unggahan Indy Barends Disorot - InsertLive",
                category = "entertainment",
                publishedAt = "2023-01-04T12:12:00Z",
                url = "https://www.insertlive.com/hot-gossip/20230104122522-7-299847/diduga-tak-sejalan-dengan-istri-indra-bekti-unggahan-indy-barends-disorot",
                urlToImage = "https://akcdn.detik.net.id/visual/2022/12/29/indy-barends-dan-indra-bekti-4_169.jpeg?wid=63&w=650&t=jpeg",
                isBookmarked = false
            )
            newsList.add(news)
        }
        return newsList
    }
}