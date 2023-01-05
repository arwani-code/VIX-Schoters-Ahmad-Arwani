package com.arwani.ahmad.schotersnews

import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.data.network.response.Article
import com.arwani.ahmad.schotersnews.data.network.response.NewsResponse
import com.arwani.ahmad.schotersnews.data.network.response.Source

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

    fun generateDummyNewsResponse(): NewsResponse {
        val newsList = ArrayList<Article>()
        for (i in 0..10) {
            val news = Article(
                author = "Asep Syaifullah",
                content = "Jakarta - Baru-baru ini ramai soal kabar kedekatan putri Lorenzo Lamas, Victoria Lamas, dengan Leonardo DiCaprio. Keduanya bahkan tertangkap kamera tengah menikmati makan malam bersama beberapa kali … [+1830 chars]",
                description =  "Lorenzo Lamas memberikan tanggapan soal kedekatan putrinya, Victoria Lamas dengan Leonardo DiCaprio. Bagaimana tanggapannya atas kejadian itu?",
                 publishedAt = "2023-01-05T01:24:39Z",
                title = "Jawaban Lorenzo Lamas Usai Putrinya Kencan dengan Leonardo DiCaprio - detikHot",
                urlToImage = "https://awsimages.detik.net.id/api/wm/2022/09/07/lorenzo-lamas-1_169.jpeg?wid=54&w=650&v=1&t=jpeg",
                url =  "https://hot.detik.com/celeb/d-6498508/jawaban-lorenzo-lamas-usai-putrinya-kencan-dengan-leonardo-dicaprio",
                source = Source(i.toString(), "Detik.com")
            )
            newsList.add(news)
        }
        return NewsResponse(articles = newsList, status = "Success", totalResults = newsList.size)
    }
}