package com.kamrul.bookcatalog.book.presentation.book_list

import com.kamrul.bookcatalog.book.domain.Book
import com.kamrul.bookcatalog.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResult: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
