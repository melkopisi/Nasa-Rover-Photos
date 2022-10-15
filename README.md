# Nasa-Rover-Photos

Simple app display Nasa Rover Photos from mars using their public API "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?api_key=DEMO_KEY&sol=1000&page=1"

## How to run?

This project needs Android Studio 4.0.0 or above with Android Gradle plugin 7.0+

It's recommended to open it using <B>Android Studio Artic Fox</B> or above

## Architecture

Clean architecture based on MVVM (Model-View-ViewModel) with Kotlin Coroutines and Flow

The following diagram shows all the layers and how each layer interacts with each other.
This architecture uses a layered software architecture.
![MVVM](https://github.com/melkopisi/Librarian/blob/master/doc/mvvm_architecture.png)
![Clean Architecture](https://github.com/melkopisi/Librarian/blob/master/doc/clean_architecture.png)

## Built With 🛠

* [Kotlin](https://kotlinlang.org/) - official programming language for Android development.
* [Coroutines](https://developer.android.com/kotlin/coroutines) - for asynchronous or
  non-blocking programming.
* [Flow](https://developer.android.com/kotlin/flow) - a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
* [Android Architecture Components](https://developer.android.com/jetpack/guide) - Part of Jetpack it's a set of libraries that help you design robust, testable, and maintainable apps.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way.
    - [Navigation component](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection (Based on Dagger 2).
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.
* [Gson](https://github.com/google/gson) A Java serialization/deserialization library to convert Java Objects into JSON and back.
* [Material Design](https://material.io/design/guidelines-overview) are interactive building blocks for creating a friendly user interface.
* [Glide](https://github.com/bumptech/glide) An image loading and caching library.
* [JUnit](https://junit.org/junit5/) A foundation framework for developer-side testing on the JVM.
* [Mockito](https://github.com/mockito/mockito) Mocking framework for unit tests.
