package com.kamrul.bookcatalog.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {
    @Upsert
    suspend fun upsertFavoriteBook(book: BookEntity)

    @Query("SELECT * FROM books")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getFavoriteBook(id: String): BookEntity?

    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteFavoriteBook(id: String)
}
