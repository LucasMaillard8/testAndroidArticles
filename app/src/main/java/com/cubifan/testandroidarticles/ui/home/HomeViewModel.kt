package com.cubifan.testandroidarticles.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cubifan.testandroidarticles.MainActivity.Companion.db
import com.cubifan.testandroidarticles.api.ApiClient
import com.cubifan.testandroidarticles.api.responses.Article
import com.cubifan.testandroidarticles.database.entities.FavoriteArticleEntity
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var articles = flow {
        emit(
            ApiClient.API_SERVICE.getNews().body()?.data?.items ?: arrayOf()
        )
    }

    fun addToFavorites(article: Article) {
        viewModelScope.launch {
            with(article) {
                db!!.favoritesDao().insertFavorites(
                    FavoriteArticleEntity(
                        title = title,
                        published = published,
                        imageUrl = if(medias.imageCount != 0) medias.images.first().original.url else ""
                    )
                )
            }
        }
    }
}