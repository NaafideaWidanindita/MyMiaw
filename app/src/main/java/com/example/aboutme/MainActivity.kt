package com.example.aboutme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.aboutme.ui.theme.AboutMeTheme
import com.example.aboutme.ui.theme.BlurYellow
import com.example.aboutme.ui.theme.Yellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AboutMeTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(navController, startDestination = "home", Modifier.padding(innerPadding)) {
            composable("home") { HomeScreen() }
            composable("list") { ListScreen(navController) }
            composable("about") { AboutScreen() }
            composable("detail/{itemId}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
                DetailScreen(itemId = itemId, navController = navController)
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomNavigation(
        backgroundColor = BlurYellow
    ) {
        BottomNavigationItem(
            selected = currentDestination == "home",
            onClick = { navController.navigate("home") },
            label = { Text("Home", color = Color.Black) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = if (currentDestination == "home") Yellow else Color.Gray
                )
            },
            selectedContentColor = Yellow,
            unselectedContentColor = Color.Gray
        )
        BottomNavigationItem(
            selected = currentDestination == "list",
            onClick = { navController.navigate("list") },
            label = { Text("List", color = Color.Black) },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "List",
                    tint = if (currentDestination == "list") Yellow else Color.Gray
                )
            },
            selectedContentColor = Yellow,
            unselectedContentColor = Color.Gray
        )
        BottomNavigationItem(
            selected = currentDestination == "about",
            onClick = { navController.navigate("about") },
            label = { Text("About", color = Color.Black) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "About",
                    tint = if (currentDestination == "about") Yellow else Color.Gray
                )
            },
            selectedContentColor = Yellow,
            unselectedContentColor = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AboutMeTheme {
        AppNavigation()
    }
}
