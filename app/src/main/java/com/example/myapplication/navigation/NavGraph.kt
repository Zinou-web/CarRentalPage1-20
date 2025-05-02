package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.cardetails.CarDetailsScreen
import com.example.myapplication.ui.screens.cardetails.GalleryScreen
import com.example.myapplication.ui.screens.home.HomeScreen
import com.example.myapplication.ui.screens.signin.CompleteProfileScreen
import com.example.myapplication.ui.screens.signin.CreateAccountScreen
import com.example.myapplication.ui.screens.signin.NewPasswordScreen
import com.example.myapplication.ui.screens.signin.SignInScreen
import com.example.myapplication.ui.screens.welcome.WelcomeScreen
import com.example.myapplication.ui.screens.second.SecondScreen
import com.example.myapplication.ui.screens.ThirdScreen.ThirdScreen

/**
 * Enum class that contains all the possible screens in our app
 * This makes it easier to manage screen routes and prevents typos
 */
enum class Screen {
    Welcome,      // The initial welcome/splash screen
    Second,       // The second screen in the flow
    Third,        // The third screen in the flow
    SignIn,       // The sign in screen
    CreateAccount, // The create account screen
    NewPassword,  // The new password screen
    CompleteProfile,  // The profile completion screen
    Home,         // The home screen with car listings
    CarDetails,   // The car details screen
    Gallery       // The gallery screen showing car photos
}

/**
 * The main navigation graph of the application
 * This sets up all possible navigation paths between screens
 *
 * @param navController The navigation controller that handles the navigation
 * @param startDestination The screen to show when the app first launches
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Screen = Screen.Welcome
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.name
    ) {
        // Welcome Screen
        composable(Screen.Welcome.name) {
            WelcomeScreen(
                // Navigate to second screen when needed
                onNextClick = {
                    navController.navigate(Screen.Second.name)
                }
            )
        }

        // Second Screen
        composable(Screen.Second.name) {
            SecondScreen(
                // Navigate back to previous screen
                onBackClick = {
                    navController.popBackStack()
                },
                // Navigate to third screen
                onNextClick = {
                    navController.navigate(Screen.Third.name)
                },
                // Skip to sign in screen
                onSkipClick = {
                    navController.navigate(Screen.SignIn.name) {
                        // Clear the back stack so user can't go back to onboarding
                        popUpTo(Screen.Welcome.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Third Screen
        composable(Screen.Third.name) {
            ThirdScreen(
                // Navigate back to previous screen
                onBackClick = {
                    navController.popBackStack()
                },
                // Navigate to sign in screen
                onNextClick = {
                    navController.navigate(Screen.SignIn.name) {
                        // Clear the back stack so user can't go back to onboarding
                        popUpTo(Screen.Welcome.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Sign In Screen
        composable(Screen.SignIn.name) {
            SignInScreen(
                // Navigate to create account screen
                onCreateAccountClick = {
                    navController.navigate(Screen.CreateAccount.name)
                },
                // Navigate to forgot password flow
                onForgotPasswordClick = {
                    navController.navigate(Screen.NewPassword.name)
                },
                // Navigate to Home on successful login
                onSignInSuccess = {
                    navController.navigateAndClear(Screen.Home.name)
                }
            )
        }

        // Create Account Screen
        composable(Screen.CreateAccount.name) {
            CreateAccountScreen(
                // Navigate to sign in screen
                onSignInClick = {
                    navController.navigate(Screen.SignIn.name) {
                        popUpTo(Screen.SignIn.name) {
                            inclusive = true
                        }
                    }
                },
                // Navigate to complete profile after successful account creation
                onCreateAccountSuccess = {
                    navController.navigate(Screen.CompleteProfile.name)
                }
            )
        }

        // New Password Screen
        composable(Screen.NewPassword.name) {
            NewPasswordScreen(
                // Navigate back to previous screen
                onBackClick = {
                    navController.popBackStack()
                },
                // Navigate to password reset
                onPasswordResetSuccess = {
                    // Return to sign in after setting new password
                    navController.navigate(Screen.SignIn.name) {
                        popUpTo(Screen.SignIn.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Complete Profile Screen
        composable(Screen.CompleteProfile.name) {
            CompleteProfileScreen(
                // Navigate back to previous screen
                onBackClick = {
                    navController.popBackStack()
                },
                // After profile completion, go to Sign In screen
                onProfileComplete = {
                    navController.navigate(Screen.SignIn.name) {
                        popUpTo(Screen.CreateAccount.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        
        // Home Screen
        composable(Screen.Home.name) {
            HomeScreen(
                // Navigate to car details when a car is clicked
                onCarClick = { carId ->
                    // Pass the car ID to the CarDetails screen
                    navController.navigate(Screen.CarDetails.name)
                },
                // Navigate to profile screen
                onProfileClick = {
                    // Could add a Profile screen later
                    // For now, just go to Sign In as a placeholder
                    navController.navigate(Screen.SignIn.name)
                },
                // Navigate to welcome screen (sign out)
                onSignOut = {
                    navController.navigateAndClear(Screen.Welcome.name)
                }
            )
        }
        
        // Car Details Screen
        composable(Screen.CarDetails.name) {
            CarDetailsScreen(
                // Navigate back to home
                onBackPressed = {
                    navController.popBackStack()
                },
                // Navigate to Gallery screen
                onGalleryClick = {
                    navController.navigate(Screen.Gallery.name)
                }
            )
        }
        
        // Gallery Screen
        composable(Screen.Gallery.name) {
            GalleryScreen(
                // Navigate back to car details
                onBackPressed = {
                    navController.popBackStack()
                },
                // Example photos - in a real app, you would pass these from the previous screen
                photos = listOf(
                    com.example.myapplication.R.drawable.mustang,
                    com.example.myapplication.R.drawable.mustang2,
                    com.example.myapplication.R.drawable.mustang3,
                    com.example.myapplication.R.drawable.mustang,
                    com.example.myapplication.R.drawable.mustang2,
                    com.example.myapplication.R.drawable.mustang3,
                    com.example.myapplication.R.drawable.mustang,
                    com.example.myapplication.R.drawable.mustang2,
                    com.example.myapplication.R.drawable.mustang3
                )
            )
        }
    }
}

/**
 * Extension function to help with navigation and clearing the back stack
 * Use this when you want to navigate to a screen and remove all previous screens from history
 */
fun NavHostController.navigateAndClear(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
} 