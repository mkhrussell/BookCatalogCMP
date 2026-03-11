package com.kamrul.bookcatalog

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.kamrul.bookcatalog.book.presentation.book_list.BookListViewModel
import com.kamrul.bookcatalog.book.presentation.book_list.BooklistScreenRoot

@Composable
@Preview
fun App() {
    MaterialTheme {
        BooklistScreenRoot(
            viewModel = remember { BookListViewModel() },
            onBookClick = {}
        )
    }
}
