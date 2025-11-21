# 📱 KMP Aticle App
<br>

## Architecture and Key Decisions
The project uses a clean, two-module KMP architecture:
- :app Module: This is the core of the application, containing all primary features and logic. It follows MVVM architecture written with Kotlin and Jetpack libraries. It uses Hilt for dependency injection and handles platform-specific tasks like background workers.
- :shared Module: A Kotlin Multiplatform module whose single responsibility is providing cache.
```
└── article
    ├── app
    │   ├── data
    │   │   ├── mapper
    │   │   ├── model
    │   │   ├── remote
    │   │   │   ├── ApiService.kt
    │   │   │   └── MockInterceptor.kt // Provides fake API data
    │   │   ├── repository
    │   │   └── worker // Background data prefetch
    │   ├── di
    │   │   └── AppModule.kt
    │   ├── domain
    │   │   ├── model
    │   │   └── repository
    │   ├── ui
    │   │   ├── mapper
    │   │   ├── model
    │   │   ├── theme
    │   │   ├── view
    │   │   │    ├── article // Composables for the article list screen
    │   │   │    ├── component // Reusable UI components
    │   │   │    └── detail // Composables for the article detail screen
    │   │   └── viewmodel
    │   └── util
    │   ├── ArticleApp.kt
    │   └── MainActivity.kt 
    │
    └── shared
        ├── cache
        └── Platform.kt


```
<br><br>

## Network vs. Backend Errors 
Error handling is managed in the :app module using a custom Result sealed class. When an operation fails, it returns a Result.Error that contains a specific ErrorType.
<br><br>


## Auto-Refresh and Background Prefetch
The app uses WorkManager to schedule a daily background job.
<br><br>


## Staleness Rule and KMP Cache
The :shared module provides a simple in-memory cache with a 1-minute. If data is older than one minute, it's considered stale, triggering a network fetch. Otherwise, the valid cache entry is served instantly.
<br><br>


## Project Status
All core features were completed: the KMP module, network and caching layers, Compose UI for list/detail views, filter functionality, error handling, and background prefetching. Optional features like UI animations, persistent database caching, and more test coverage were skipped to prioritize core functionality.
<br><br>
