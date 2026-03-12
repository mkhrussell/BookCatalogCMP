package com.kamrul.bookcatalog

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.kamrul.bookcatalog.book.data.network.KtorRemoteBookDatasource
import com.kamrul.bookcatalog.book.data.repository.DefaultBookRepository
import com.kamrul.bookcatalog.book.presentation.book_list.BookListViewModel
import com.kamrul.bookcatalog.book.presentation.book_list.BooklistScreenRoot
import com.kamrul.bookcatalog.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine

@Composable
@Preview
fun App(
    engine: HttpClientEngine
) {
    MaterialTheme {
        BooklistScreenRoot(
            viewModel = remember { BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDatasource(
                        httpClient = HttpClientFactory.create(
                            engine = engine
                        )
                    )
                )
            ) },
            onBookClick = {}
        )
    }
}
