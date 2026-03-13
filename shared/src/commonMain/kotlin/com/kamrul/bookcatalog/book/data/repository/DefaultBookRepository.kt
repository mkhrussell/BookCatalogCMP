package com.kamrul.bookcatalog.book.data.repository

import androidx.sqlite.SQLiteException
import com.kamrul.bookcatalog.book.data.database.FavoriteBookDao
import com.kamrul.bookcatalog.book.data.mappers.toBook
import com.kamrul.bookcatalog.book.data.mappers.toBookEntity
import com.kamrul.bookcatalog.book.data.network.RemoteBookDataSource
import com.kamrul.bookcatalog.book.domain.Book
import com.kamrul.bookcatalog.book.domain.BookRepository
import com.kamrul.bookcatalog.core.domain.DataError
import com.kamrul.bookcatalog.core.domain.EmptyResult
import com.kamrul.bookcatalog.core.domain.Result
import com.kamrul.bookcatalog.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao
): BookRepository {
    override suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { responseDto ->
                responseDto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDeDescription(bookId: String): Result<String?, DataError> {
        val localBook = favoriteBookDao.getFavoriteBook(bookId)

        return if (localBook == null) {
            remoteBookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localBook.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsertFavoriteBook(book.toBookEntity())

            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorite(id: String) {
        favoriteBookDao.deleteFavoriteBook(id)
    }
}
