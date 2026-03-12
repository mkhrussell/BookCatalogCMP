package com.kamrul.bookcatalog.di

import com.kamrul.bookcatalog.book.data.network.KtorRemoteBookDatasource
import com.kamrul.bookcatalog.book.data.network.RemoteBookDataSource
import com.kamrul.bookcatalog.book.data.repository.DefaultBookRepository
import com.kamrul.bookcatalog.book.domain.BookRepository
import com.kamrul.bookcatalog.book.presentation.SelectedBookViewModel
import com.kamrul.bookcatalog.book.presentation.book_list.BookListViewModel
import com.kamrul.bookcatalog.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        HttpClientFactory.create(
            engine = get()
        )
    }
    singleOf(::KtorRemoteBookDatasource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
}
