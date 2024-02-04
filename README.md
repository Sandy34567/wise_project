

Overview
This project is a Compose-based Android application for displaying and managing a list of drinks. It includes screens for viewing categories, details of a selected drink, and search functionality.

Screens

Category Screen

Drink Details Screen

ViewModels
Composables
CategoryItem
Navigation
UI Components
OutlinedTextField
LazyVerticalGrid
Dependencies
Compose
Hilt
Coil
The project follows the MVVM (Model-View-ViewModel) architecture pattern. It utilizes Compose for building the UI, Hilt for dependency injection, and Coil for image loading.

Screens
Category Screen
The Category Screen displays a list of drinks, allows searching, and provides options to view the details of a selected drink. It includes:

Header with custom-styled text.
Search functionality with an OutlinedTextField.
UI elements for displaying the latest added category.
Grid of drinks using LazyVerticalGrid.
Drink Details Screen
The Drink Details Screen presents detailed information about a selected drink. It includes:

Image of the drink with rounded corners.
Drink name, ingredients, measures, and instructions.
Back button for navigating to the previous screen.

CoctailViewMode: Manages the list of drinks and search functionality.
SharedViewModel: Shared view model for communication between screens.
Composables
CategoryItem Composable
The CategoryItem composable represents an individual item in the category grid. It includes:

Box with a clickable behavior.
Image of the drink with a rounded corner and content scale.
Text overlay with the drink name.
Navigation
Navigation is handled using the Jetpack Navigation library. Screens are navigated using the NavHostController provided by the Navigation component.




