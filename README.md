# Homework

This is my completed assessment for the Fleetio interview.  This is a typical list/details Android interview homework problem.  This code should only be viewed by Fleetio employees.  

## Project Overview

The app fetches a list of Fleetio vehicles from the [Fleetio API](https://developer.fleetio.com/docs/category/api) and displays them in a list. Paging is supported by swiping up on the list page.  When a vehicle is tapped, the app navigates to a detailed view showing additional information about the selected vehicle.  

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVI
- **Navigation:** Compose Navigation
- **Networking:** Retrofit
- **Dependency Injection:** Dagger Hilt
- **Testing:** MockK, Espresso
- **Other:** Coil, Lottie, Timber
- **Build Scripts:** Kotlin DSL
   
## Implementation Decisions

**Single Module:** This app uses a single module due to its small project size. For larger and more complex projects, a modular architecture—such as separating domain, data, and feature modules—would be a cleaner and more scalable approach.

**Clean Architecture:** While separating the data and UI layers with a domain layer may seem excessive for this project, it serves as an intentional demonstration of clean architecture principles.  

**MVI/MVVM:** I decided to use MVI after seeing it in the job description and having primarily used MVI over MVVM for the past two years. Although it may seem overkill for an app of this size, I thought it would be nice to use in order to manage states and events on the explore page.

**Paging:** I implemented a custom paging solution instead of using the Paging 3 library because each item only handles a small amount of data, and in my experience the Android Paging libraries can be difficult to squeeze into a true clean architecture.

**Assumptions:** I've made some DTO nullability decisions that could probably be improved. I've also locked the app to portrait orientation since my last two companies have used this mode, and I've noticed that Fleetio Go also operates in portrait mode.

**Future Improvements:** Additional unit tests and screenshot tests could enhance the project.  There are TODOs throughout to illustrate what I could have accomplished given more time. 

## Clean Architecture

This project adheres to clean architecture principles to ensure clear separation of concerns. The data and UI layers are
fully decoupled, with the domain layer serving as the intermediary.  Note: UseCases were not utilized in this project due to its small scope, and they
should only be used for complex domain logic that most apps will not need.

<img width="1316" alt="CleanArchitecture" src="https://github.com/user-attachments/assets/8ff5fbff-b7b0-481e-b424-396d7e9d3353" />


## Setup

### Prerequisites
- Android Studio Ladybug or newer

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/lmorda/Homework.git
   ```
2. Open the project in Android Studio
3. Sync Gradle
4. Run the app on an emulator or a physical device

## Asset Acknowledgement

Thanks for the app icon!

https://www.flaticon.com/authors/agung-rama
