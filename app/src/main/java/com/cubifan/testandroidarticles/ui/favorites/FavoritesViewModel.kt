package com.cubifan.testandroidarticles.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cubifan.testandroidarticles.MainActivity.Companion.db
import com.cubifan.testandroidarticles.api.responses.Article
import com.cubifan.testandroidarticles.database.entities.FavoriteArticleEntity
import com.cubifan.testandroidarticles.database.entities.toArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    var articles = flow {
        val favorites = db!!.favoritesDao().getFavorites().map { it.toArticle() }
        if (!favorites.isNullOrEmpty()) emit(favorites.toTypedArray())
    }

    fun removeArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            with(article) {
                db!!.favoritesDao().deleteFavorite(
                    FavoriteArticleEntity(
                        title = title,
                        published = published,
                        imageUrl = if (medias.imageCount != 0) medias.images.first().original.url else ""
                    )
                )
            }
        }
    }
}