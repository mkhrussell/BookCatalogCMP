# BookCatalogCMP

BookCatalogCMP is a Kotlin Multiplatform (KMP) book discovery app built with Compose Multiplatform (CMP). It shares UI, presentation, domain, networking, persistence, and dependency injection across Android, Desktop, and iOS, while keeping platform startup and infrastructure wiring in platform-specific source sets.

The app searches books from the Open Library API, shows book details, and lets users save favorites locally with Room.

## Targets

- Android application in `androidApp`
- Desktop JVM application in `desktopApp`
- Shared multiplatform code in `shared`
- iOS host app in `iosApp`

The repository still contains some `jsMain` and `wasmJsMain` source files in `shared`, but because of Room database configuration issue, the web target is currently disabled in Gradle and `webApp` is not included in `settings.gradle.kts`.

## Features

- Search books from Open Library
- View book details, including fetched descriptions
- Save and remove favorite books locally
- Shared navigation and shared view models across platforms
- Desktop packaging with platform-specific icons for Windows, macOS, and Linux

## Tech Stack

- Kotlin Multiplatform
- Compose Multiplatform
- Compose Navigation
- Koin for dependency injection
- Ktor client for networking
- Room with bundled SQLite for local persistence
- Coil 3 for image loading
- AndroidX Lifecycle ViewModel

## Project Structure

```text
.
|-- androidApp/   Android launcher app
|-- desktopApp/   Desktop launcher app and packaging config
|-- iosApp/       Xcode host app for iOS
|-- shared/       Shared UI, domain, data, DI, resources, and platform source sets
|-- gradle/       Version catalog and Gradle wrapper support
```

### Shared module layout

`shared/src/commonMain/kotlin/com/kamrul/bookcatalog`

- `app`
  Shared Compose app entry and typed navigation graph
- `book/domain`
  Domain model and repository contract
- `book/data`
  DTOs, Ktor remote datasource, Room database, DAO, mappers, repository implementation
- `book/presentation`
  View models, state, actions, screens, and reusable UI components
- `core`
  Shared result/error abstractions, HTTP helpers, theme values, and UI text mapping
- `di`
  Shared and platform-specific Koin modules

### Platform entry points

- Android app startup: [androidApp/src/main/kotlin/com/kamrul/bookcatalog/BookApplication.kt](androidApp/src/main/kotlin/com/kamrul/bookcatalog/BookApplication.kt)
- Android activity: [androidApp/src/main/kotlin/com/kamrul/bookcatalog/MainActivity.kt](androidApp/src/main/kotlin/com/kamrul/bookcatalog/MainActivity.kt)
- Desktop app startup: [desktopApp/src/main/kotlin/com/kamrul/bookcatalog/main.kt](desktopApp/src/main/kotlin/com/kamrul/bookcatalog/main.kt)
- Shared Compose root: [shared/src/commonMain/kotlin/com/kamrul/bookcatalog/app/App.kt](shared/src/commonMain/kotlin/com/kamrul/bookcatalog/app/App.kt)

## Architecture

The app follows a layered shared-code architecture:

### Presentation

- `BookListViewModel` manages search input, debounced queries, loading state, errors, and favorite list observation
- `BookDetailViewModel` loads the selected book description and favorite state
- `SelectedBookViewModel` shares the current selected book between list and detail destinations
- Screens are implemented in shared Compose UI so Android, Desktop, and iOS use the same flow

### Domain

- `Book` is the shared domain model
- `BookRepository` defines operations for search, detail loading, favorite observation, and favorite mutations

### Data

- `KtorRemoteBookDatasource` calls the Open Library API
- `DefaultBookRepository` combines remote data with local favorites
- Room stores favorite books in `FavoriteBookDatabase`
- Mappers convert between DTOs, entities, and domain models

### Dependency injection

- `initKoin()` starts Koin with `sharedModule + platformModule`
- Shared module provides HTTP client creation, repository bindings, database, DAO, and view models
- Platform modules provide the Ktor engine and platform-specific database factory

## Navigation flow

Navigation is defined in the shared module with typed routes:

- `Route.BookList`
- `Route.BookDetail(id)`

The shared `App()` composable builds a single navigation graph and uses a shared Koin-backed view model to hand off the selected book between list and detail screens.

## Data sources

### Remote API

The app uses Open Library:

- Search endpoint: `https://openlibrary.org/search.json`
- Work details endpoint: `https://openlibrary.org/works/{id}.json`

No API key is required.

### Local database

Favorites are stored in a Room database named `favorite_books_db`.

On JVM/Desktop the database is stored under:

- Windows: `%APPDATA%/BookCatalog`
- macOS: `~/Library/Application Support/BookCatalog`
- Linux: `~/.local/share/BookCatalog`

## Build Requirements

- JDK 21 or newer
- Android SDK for Android builds
- Xcode for running the iOS app

Versions currently used by the project are managed in [gradle/libs.versions.toml](gradle/libs.versions.toml), including:

- Kotlin `2.3.10`
- Compose Multiplatform `1.10.0`
- Android Gradle Plugin `9.0.0`

## Run the app

### Android

```powershell
.\gradlew.bat :androidApp:assembleDebug
```

To launch on a device or emulator from Android Studio, run the `androidApp` configuration.

### Desktop

```powershell
.\gradlew.bat :desktopApp:run
```

The desktop window is configured as a portrait phone-sized window (`412dp x 915dp`) and uses a runtime window icon from Compose resources.

### iOS

Open [iosApp](iosApp) in Xcode and run the app from there.

## Packaging the desktop app

Desktop packaging is configured in [desktopApp/build.gradle.kts](desktopApp/build.gradle.kts).

Available target formats:

- `MSI` for Windows
- `DMG` for macOS
- `DEB` for Linux

Package for the current OS:

```powershell
.\gradlew.bat :desktopApp:packageDistributionForCurrentOS
```

Desktop packaging icons:

- [desktopApp/src/main/resources/icons/windows-icon.ico](desktopApp/src/main/resources/icons/windows-icon.ico)
- [desktopApp/src/main/resources/icons/macos-icon.icns](desktopApp/src/main/resources/icons/macos-icon.icns)
- [desktopApp/src/main/resources/icons/linux-icon.png](desktopApp/src/main/resources/icons/linux-icon.png)

## Important files

- Shared navigation and app shell: [shared/src/commonMain/kotlin/com/kamrul/bookcatalog/app/App.kt](shared/src/commonMain/kotlin/com/kamrul/bookcatalog/app/App.kt)
- DI setup: [shared/src/commonMain/kotlin/com/kamrul/bookcatalog/di/Modules.kt](shared/src/commonMain/kotlin/com/kamrul/bookcatalog/di/Modules.kt)
- Koin bootstrap: [shared/src/commonMain/kotlin/com/kamrul/bookcatalog/di/initKoin.kt](shared/src/commonMain/kotlin/com/kamrul/bookcatalog/di/initKoin.kt)
- Book repository: [shared/src/commonMain/kotlin/com/kamrul/bookcatalog/book/data/repository/DefaultBookRepository.kt](shared/src/commonMain/kotlin/com/kamrul/bookcatalog/book/data/repository/DefaultBookRepository.kt)
- Remote datasource: [shared/src/commonMain/kotlin/com/kamrul/bookcatalog/book/data/network/KtorRemoteBookDatasource.kt](shared/src/commonMain/kotlin/com/kamrul/bookcatalog/book/data/network/KtorRemoteBookDatasource.kt)
- Room database: [shared/src/commonMain/kotlin/com/kamrul/bookcatalog/book/data/database/FavoriteBookDatabase.kt](shared/src/commonMain/kotlin/com/kamrul/bookcatalog/book/data/database/FavoriteBookDatabase.kt)
- Android entry: [androidApp/src/main/kotlin/com/kamrul/bookcatalog/MainActivity.kt](androidApp/src/main/kotlin/com/kamrul/bookcatalog/MainActivity.kt)
- Desktop entry: [desktopApp/src/main/kotlin/com/kamrul/bookcatalog/main.kt](desktopApp/src/main/kotlin/com/kamrul/bookcatalog/main.kt)

## Notes

- `settings.gradle.kts` currently includes `shared`, `androidApp`, and `desktopApp`
- iOS is supported through the Xcode host app and shared iOS source sets
- Web code is scaffolded but not currently part of the active Gradle build
