package com.kamrul.bookcatalog.book.data.network

import com.kamrul.bookcatalog.book.domain.Book
import com.kamrul.bookcatalog.core.domain.DataError
import com.kamrul.bookcatalog.core.domain.Result
import io.ktor.client.HttpClient

class KtorRemoteBookDatasource(
    private val httpClient: HttpClient,
) {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<List<Book>, DataError.Remote> {
        TODO()
    }
}
