package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    CompleteProfile  // The profile completion screen
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
                    navController.navigate(Screen.SignIn.name)
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
                    navController.navigate(Screen.SignIn.name)
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
                }
            )
        }

        // Create Account Screen
        composable(Screen.CreateAccount.name) {
            CreateAccountScreen(
                // Navigate to sign in screen
                onSignInClick = {
                    navController.navigate(Screen.SignIn.name)
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
                // Navigate to complete profile after password reset
                onPasswordResetSuccess = {
                    navController.navigate(Screen.CompleteProfile.name)
                }
            )
        }

        // Complete Profile Screen
        composable(Screen.CompleteProfile.name) {
            CompleteProfileScreen(
                // Navigate back to previous screen
                onBackClick = {
                    navController.popBackStack()
                }
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