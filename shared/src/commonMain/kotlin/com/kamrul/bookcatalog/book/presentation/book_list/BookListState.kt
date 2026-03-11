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

// Used only for preview and testing
val dummyBooks = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://dummy.com/",
        authors = listOf("Author Name"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.6787,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}
