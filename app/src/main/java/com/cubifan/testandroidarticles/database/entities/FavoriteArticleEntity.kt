package com.cubifan.testandroidarticles.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cubifan.testandroidarticles.api.responses.Article
import com.cubifan.testandroidarticles.api.responses.ImageData
import com.cubifan.testandroidarticles.api.responses.Medias
import com.cubifan.testandroidarticles.api.responses.OriginalData

@Entity(tableName = "favorites_table")
data class FavoriteArticleEntity(
    @PrimaryKey
    val title: String,
    val published: String,
    val imageUrl: String
)

fun FavoriteArticleEntity.toArticle() =
    Article(
        title,
        published,
        Medias(
            if (imageUrl.isNotEmpty()) 1 else 0,
            arrayOf(ImageData(OriginalData(imageUrl)))
        )
    )