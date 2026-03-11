package com.kamrul.bookcatalog.book.data.network

import com.kamrul.bookcatalog.core.domain.Result
import com.kamrul.bookcatalog.book.data.dto.SearchResponseDto
import com.kamrul.bookcatalog.core.domain.DataError

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>
}
