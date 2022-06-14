package com.cubifan.testandroidarticles.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.cubifan.testandroidarticles.database.entities.FavoriteArticleEntity

@Dao
interface FavoritesDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertFavorites(article: FavoriteArticleEntity)

    @Query("SELECT * FROM favorites_table")
    suspend fun getFavorites(): Array<FavoriteArticleEntity>

    @Delete
    suspend fun deleteFavorite(article: FavoriteArticleEntity)
}