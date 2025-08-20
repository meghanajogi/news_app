## Mobile App - Listing and Details Screens

A clean architecture mobile application built with Kotlin, featuring Listing and Details screens, fllowing MVVM, Jetpack compose Hilt, Coroutines with StateFlow and unit tests.

## Tech Stack

- Langauge : Kotlin
- UI : Jetpack Compose
- Architecture : Clean Architecture with MVVM (Model - View - ViewModel) pattern
- Dependency Injection : Hilt
- Concurrency : Kotlin Coroutines + StateFlow + Flow
- Testing :  JUnit/Mockito

## Architecture Overview
- **Clean Architecture and MVVM**
Layers are separated into data, domin and ui to enusre modularity, testability andseparation of concerns.
- **Jetpack Compose for UI**
Declarative UI code with composable for the Listing and Details screen for concise, readable UIs.
- **Hilt for DI**
Manages dependencies across ViewModels, repositoroes and data sources seamlessly via Hilt modules.
- **Coroutines + StateFlow + Flow**
Asynchronous data flow handled via coroutines and state holding via StateFlow, enabling reactive and streamlined UI updates.
- **Unit Testing**
ViewModels and domain logic are covered with unit tests using JUnit and Mockito for mocking dependencies.
