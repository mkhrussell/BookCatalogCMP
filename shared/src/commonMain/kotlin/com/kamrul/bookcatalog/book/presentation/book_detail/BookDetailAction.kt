package com.kamrul.bookcatalog.book.presentation.book_detail

import com.kamrul.bookcatalog.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavoriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}
