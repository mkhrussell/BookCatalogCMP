package com.kamrul.bookcatalog.book.domain

import com.kamrul.bookcatalog.core.domain.DataError
import com.kamrul.bookcatalog.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote>
}
