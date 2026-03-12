package com.kamrul.bookcatalog.book.data.database

import androidx.room.RoomDatabaseConstructor

expect object BookDatabaseConstructor: RoomDatabaseConstructor<FavoriteBookDatabase> {
    override fun initialize(): FavoriteBookDatabase
}
