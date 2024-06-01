# Summary

Android application to log in users, verifying their credentials (email and password) in a Firestore environment database.
If the user authenticates successfully, they will navigate to the Home activity. 
The Home activity displays a list of movement cards that are fetched from a Firestore database.
The application implements an onboarding module to register the user, and all these fields (name, surname, email, and password)
will be saved in a Firestore database.

# Language
The entire project is designed with kotlin language.

- Use of Jetpack compose framework for Login and Register module.
- Use of XML files and AppCompactActivity class in Home module.

## Project structure
The project implement the clean architecture guidelines as follows:

## Presentation

Defines the code related to the construction of views and UI logic, this layer will show the data from de domain module (use case).
Use of Jetpack compose, MVVM and State flow in this layer.

## Domain

Use cases are defined in this module and encapsulate the business logic of the aplication,
for instance complex alghoritms can be designed in this module.
They take input and transform it into output in a way specific to the app.

## Data

Provides the data that will be exposed in the UI Layer after mapping it from the domain layer.
The entry points to the data layer are always the repository and Data source.
The repository acts as a bridge between the domain layer and Data source encapsulate the network client or a instance db local.

# Testing

**Android testing**

Instrumentation test are defined for the UI, simulating user interactions and validate the behavior of the user interface.
LoginScreen, RegisterScreen  and another components were tested.

**Unit testing**

These tests focus on testing the individual components and their behavior in isolation.
This project implements ViewModels, UseCases and Repositories test cases.

## Banking Application

| Login Path    | Register Path |
| ------------- | ------------- |
| <video src =https://github.com/EduardoAlbertoPalacios/SampleBankingApp/assets/26733580/922919e8-6cb7-45e8-95f4-831789f9ed6b> |  <video src =https://github.com/EduardoAlbertoPalacios/SampleBankingApp/assets/26733580/785235e0-9c40-46a7-92cc-0f653c04ec8e > 
