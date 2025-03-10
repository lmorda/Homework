# Homework

This is my completed assessment for the Fleetio interview.  This is a typical list/details Android interview homework problem.  The project utilizes the latest Android libraries including Jetpack Compose,
Dagger Hilt, Retrofit, Coil, Timber, MockK, Espresso, and Kotlin DSL gradle build scripts.  Note: This code should only be viewed by Fleetio employees.  

## Implementation Decisions

**Single Module:** This app uses a single module due to its small project size. For larger and more complex projects, a modular architecture—such as separating domain, data, and feature modules—would be a cleaner and more scalable approach.

**Clean Architecture:** While separating the data and UI layers with a domain layer may seem excessive for this project, it serves as an intentional demonstration of clean architecture principles.  

**MVI** I used MVI after noting its relevance in the job description and having used it as my primary architecture for the past two years. Although it may seem overkill for an app of this size, I chose to use it to showcase its utility managing state and events (especially on the explore page).

**Paging:** I implemented a custom paging solution instead of using the Paging 3 library because the project deals with a small amount of data for each item making a third-party library unnecessary for this use case.  I also prefer clean architecture where possible and Paging does not fit true clean architectures.

**Assumptions:** This project assumes that key DTO fields, specifically vehicle status, vehicle type, and name—are non-null. I've locked the app to portrait orientation since most of my previous projects have used this mode, and I've noticed that Fleetio Go also operates in portrait mode.

**Future Improvements:** Additional unit tests and screenshot tests could enhance the project.  I've also left TODOs throughout to illustrate what I would have accomplished given more time.  And the domain/data object field nullability could probably be improved. 

## Project Overview

The app fetches a list of Fleetio vehicles from the [Fleetio API](https://developer.fleetio.com/docs/category/api) and displays them in a list. Paging is supported by swiping up on the list page.  When a vehicle is tapped, the app navigates to a detailed view showing
additional information about the selected vehicle.  

## Libraries

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVI
- **Navigation:** Compose Navigation
- **Networking:** Retrofit
- **Dependency Injection:** Dagger Hilt
- **Testing:** MockK, Espresso
- **Other:** Coil, Lottie, Timber
- **Build Scripts:** Kotlin DSL

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

## Clean Architecture

This project adheres to clean architecture principles to ensure clear separation of concerns. The data and UI layers are
fully decoupled, with the domain layer serving as the intermediary.  Note: UseCases were not utilized in this project due to its small scope, and they
should only be used for complex domain logic that most apps will not need.
<img width="1243" alt="Screenshot 2025-01-16 at 8 56 30 AM" src="https://github.com/user-attachments/assets/f0bc299f-e7ef-434c-a8c5-76f54d87e54d" />


## Asset Acknowledgement

Thanks for the app icon!

https://www.flaticon.com/authors/agung-rama
