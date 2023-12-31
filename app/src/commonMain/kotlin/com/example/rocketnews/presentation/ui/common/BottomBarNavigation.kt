//package com.example.rocketnews.presentation.ui.common
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.interaction.Interaction
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.Icon
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.ArrowForward
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.outlined.ArrowBack
//import androidx.compose.material.icons.outlined.ArrowForward
//import androidx.compose.material.icons.outlined.Email
//import androidx.compose.material.icons.outlined.Home
//import androidx.compose.material3.LocalAbsoluteTonalElevation
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBarItemDefaults
//import androidx.compose.material3.surfaceColorAtElevation
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.unit.dp
//import cafe.adriel.voyager.core.screen.Screen
//import cafe.adriel.voyager.navigator.Navigator
//import com.example.rocketnews.presentation.ui.screens.news.NewsScreen
//import com.example.rocketnews.presentation.ui.screens.rockets.RocketsScreen
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.emptyFlow
//
//data class BottomNavigationItem(
//    val selectedIcon: ImageVector,
//    val unselectedIcon: ImageVector,
//    val content: Screen
//)
//
//class NoRippleInteractionSource : MutableInteractionSource {
//
//    override val interactions: Flow<Interaction> = emptyFlow()
//
//    override suspend fun emit(interaction: Interaction) {}
//
//    override fun tryEmit(interaction: Interaction) = true
//}
//
//@Composable
//fun BottomBarNavigation(navigator: Navigator, index: Int) {
//    val screens = listOf(
//        BottomNavigationItem(
//            selectedIcon = Icons.Outlined.Home,
//            unselectedIcon = Icons.Filled.Home,
//            content = NewsScreen()
//        ), BottomNavigationItem(
//            selectedIcon = Icons.Outlined.Email,
//            unselectedIcon = Icons.Filled.Email,
//            content = RocketsScreen()
//        )
//    )
//
//    var selectedScreenIndex by rememberSaveable {
//        mutableStateOf(index)
//    }
//
//    NavigationBar(Modifier.height(70.dp), containerColor = Color.Blue.copy(0.8f)) {
//        screens.forEachIndexed { index, screen ->
//            NavigationBarItem(
//                selected = selectedScreenIndex == index,
//                onClick = {
//                    selectedScreenIndex = index
//                    navigator.push(screen.content)
//                },
//                interactionSource = NoRippleInteractionSource(),
//                icon = {
//                    Icon(
//                        imageVector = if (index != selectedScreenIndex) {
//                            screen.selectedIcon
//                        } else screen.unselectedIcon,
//                        contentDescription = null,
//                        Modifier.size(40.dp)
//                    )
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = Color.Red,
//                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
//                        LocalAbsoluteTonalElevation.current
//                    )
//                )
//            )
//        }
//    }
//}
//
