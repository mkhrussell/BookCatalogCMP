package com.kamrul.bookcatalog.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamrul.bookcatalog.book.domain.Book
import com.kamrul.bookcatalog.book.domain.BookRepository
import com.kamrul.bookcatalog.core.domain.onError
import com.kamrul.bookcatalog.core.domain.onSuccess
import com.kamrul.bookcatalog.core.domain.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val bookRepository: BookRepository
): ViewModel() {
    private val cachedBooks = emptyList<Book>()

    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    fun onAction(action: BookListAction) {
        when(action) {
            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is BookListAction.OnBookClick -> {
            }
            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResult = cachedBooks
                            )
                        }
                    }
                    query.length >= 2 -> {
                        searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) {
        _state.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            bookRepository
                .searchBooks(query)
                .onSuccess { searchResult ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            searchResult = searchResult,
                            errorMessage = null
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            searchResult = emptyList(),
                            errorMessage = error.toUiText()
                        )
                    }
                }
        }
    }
}
