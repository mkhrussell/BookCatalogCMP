package com.kamrul.bookcatalog.book.domain

import com.kamrul.bookcatalog.core.domain.DataError
import com.kamrul.bookcatalog.core.domain.EmptyResult
import com.kamrul.bookcatalog.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote>

    suspend fun getBookDeDescription(
        bookId: String
    ): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorite(id: String)
}
