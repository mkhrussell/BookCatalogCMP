package com.kamrul.bookcatalog.book.presentation.book_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kamrul.bookcatalog.book.domain.Book
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BooklistScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onBookClick: (Book) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BooklistScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is BookListAction.OnBookClick -> onBookClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun BooklistScreen(
    state: BookListState,
    onAction: (BookListAction) -> Unit,
) {
    Text("My Booklist")
}
