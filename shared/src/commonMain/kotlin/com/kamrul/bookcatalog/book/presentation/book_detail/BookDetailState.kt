package com.kamrul.bookcatalog.book.presentation.book_detail

import com.kamrul.bookcatalog.book.domain.Book

data class BookDetailState(
    val book: Book? = null,
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
)
