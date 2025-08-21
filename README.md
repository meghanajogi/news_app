## Mobile App - Listing and Details Screens

A clean-architecture android mobile app in Kotlin, featuring Listing and Details screens, built with MVVM, Jetpack compose, Hilt for DI, Coroutines with StateFlow for reactive data flow, Coil for image loading and unit tests for ViewModel logic

## Tech Stack

- Language : Kotlin
- UI : Jetpack Compose
- Architecture : Clean Architecture with MVVM (Model - View - ViewModel) pattern
- Dependency Injection : Hilt
- Concurrency : Kotlin Coroutines + StateFlow + Flow
- Testing :  JUnit/Mockito

## Architecture Overview
- **Clean Architecture and MVVM**
Layers are separated into data, domain and UI to enusue modularity, testability andseparation of concerns.
- **Jetpack Compose for UI**
Declarative UI code with composable for the Listing and Details screen for concise, readable UIs.
- **Hilt for DI**
Manages dependencies across ViewModels, repositories and data sources seamlessly via Hilt modules.
- **Coroutines + StateFlow + Flow**
Asynchronous data flow handled via coroutines and state holding via StateFlow, enabling reactive and streamlined UI updates.
- **Unit Testing**
ViewModels and domain logic are covered with unit tests using JUnit and Mockito for mocking dependencies.

## Future Enhancements
- **Offline Support with Caching** : Add Room database or DataStore for local persistence and offline-first support.
- **Pagination** - Implement pagination for large datasets in the Listing Screen.
-  **Error Handling and UI States**: Introduce a unified error handling mechanism with retry actions and user-friendly error message.
-  **UI Improvements** : Enhance theming (dark mode, dynamic colors with Material 3) and accessibility features also change splash screen and app icon.
-  **API Integration Improvements** : Add support for multiple endpoints, authentication(OAuth/JWT) and secure API handling.
-  **CI/CD Integration** : Automate build, test and deployment pipelines using GitHub Actions.
-   **Multimodule** : Split features into Gradle modules for better scalability and faster builds.
   **Internationalization** : Add multi-language support for wider usability.
