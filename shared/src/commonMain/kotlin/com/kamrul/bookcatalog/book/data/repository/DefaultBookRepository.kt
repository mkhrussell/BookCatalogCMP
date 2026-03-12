package com.kamrul.bookcatalog.book.data.repository

import com.kamrul.bookcatalog.book.data.mappers.toBook
import com.kamrul.bookcatalog.book.data.network.RemoteBookDataSource
import com.kamrul.bookcatalog.book.domain.Book
import com.kamrul.bookcatalog.book.domain.BookRepository
import com.kamrul.bookcatalog.core.domain.DataError
import com.kamrul.bookcatalog.core.domain.Result
import com.kamrul.bookcatalog.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
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
        return remoteBookDataSource
            .getBookDetails(bookId)
            .map { it.description }
    }
}
