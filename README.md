# PhotoAlbum App
This application leverages the [JSONPlaceholder API](https://jsonplaceholder.typicode.com) to exhibit album lists and the associated photo details.
<br><br>

## Application Structure 📚
This project primarily adopts the Model-View-ViewModel (MVVM) pattern and certain principles of clean architecture, emphasizing the separation of concerns and encapsulation. Despite not strictly adhering to every facet of clean architecture, such as the absence of **Use Cases** and exact division of modules like **Domain** and **Data**, the application is designed with a focus on maintainability and scalability.

```
└── photo-album
    ├── data  // Handles API response models and network requests
    │   └── remote
    │       └── model (AlbumModel.kt, PhotoModel)
    │       └── WebService
    ├── di  // Contains all dependencies, provided in AppModule
    │   └── AppModule
    ├── domain  // Hosts business logic components (repositories, mappers)
    │   ├── model (AlbumItem, PhotoItem)
    │   ├── PhotoMapper
    │   └── PhotoRepository
    ├── ui  // Houses UI-related classes (components, screens)
    │   ├── album (AlbumScreen, AlbumUiState, AlbumViewModel)
    │   ├── component (AlbumCard, DetailView, ErrorView, PhotoCard)
    │   ├── photo (PhotoDetailScreen, PhotoScreen, PhotoUiState, PhotoViewModel)
    │   ├── theme // Contains classes related to the application's UI.
    │   └── MainActivity
    ├── util  // Includes utility and exception handling classes
    │   ├── exception (AppException, ExceptionExtension)
    │   ├── Constant
    │   ├── Route
    │   └── UiUtil
    └── PhotoAlbumApp
```
<br>

## Features 🚀
- Displays all available albums.
- Shows photos within each album.
- Provides a detailed view for each photo.
- Asynchronous data loading.
- Utilizes popular libraries for network requests and dependency injection.
<br>


## Tech Stack 💻
The following libraries and tools were utilized in the project:

- **Kotlin** as the main language.
- **Jetpack Compose** for building the UI.
- **AndroidX Paging Library** for handling pagination in the application.
- **Navigation-Compose** for handling in-app navigation.
- **Coil** for image loading.
- **Kotlin Coroutines & Flow** for handling asynchronous tasks.
- **Hilt** for dependency injection.
- **Retrofit** for making network requests.
- **OkHttp3** for implementing interceptor, logging and networking.
- **GSON Converter** for parsing JSON.
- **JUnit, MockK and Kotlinx Coroutines Test** for Testing.
<br>


## Future Improvements 🛠
While the project is functional and robust, there are areas that could see improvements in the future:

- **Paging**: Since the JSONPlaceholder API does not support pagination, the app currently does not feature pagination capabilities. If the API were to support it in the future, pagination would be an ideal addition.
- **Expanded Test Coverage**: More comprehensive unit and UI tests to ensure the app's stability.
- **User Experience**: Refine the UI and UX design of the app for a more engaging user experience.
<br><br>
