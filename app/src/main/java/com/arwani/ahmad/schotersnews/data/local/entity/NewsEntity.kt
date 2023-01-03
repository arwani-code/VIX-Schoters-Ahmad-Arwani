package com.arwani.ahmad.schotersnews.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arwani.ahmad.schotersnews.data.local.DatabaseConstant
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = DatabaseConstant.news)
class NewsEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = DatabaseConstant.id)
    val id: Int = 0,

    @field:ColumnInfo(name = DatabaseConstant.title)
    val title: String,

    @field:ColumnInfo(name = DatabaseConstant.category)
    val category: String,

    @field:ColumnInfo(name = DatabaseConstant.published_at)
    val publishedAt: String,

    @field:ColumnInfo(name = DatabaseConstant.url_to_image)
    val urlToImage: String? = null,

    @field:ColumnInfo(name = DatabaseConstant.url)
    val url: String? = null,

    @field:ColumnInfo(name = DatabaseConstant.bookmarked)
    var isBookmarked: Boolean
): Parcelable