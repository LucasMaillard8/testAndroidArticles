package com.cubifan.testandroidarticles.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cubifan.testandroidarticles.database.entities.FavoriteArticleEntity
@Database(
    entities = [
        FavoriteArticleEntity::class
    ],
    version = 1
)

abstract class ArticlesAppDatabase: RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    //Migrations here
}
