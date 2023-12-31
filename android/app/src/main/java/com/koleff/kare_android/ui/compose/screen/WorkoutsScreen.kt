package com.koleff.kare_android.ui.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.koleff.kare_android.ui.compose.LoadingWheel
import com.koleff.kare_android.ui.compose.banners.WorkoutBannerList
import com.koleff.kare_android.ui.compose.WorkoutSegmentButton
import com.koleff.kare_android.ui.compose.banners.WorkoutList
import com.koleff.kare_android.ui.compose.scaffolds.MainScreenScaffold
import com.koleff.kare_android.ui.view_model.WorkoutViewModel

@Composable
fun WorkoutsScreen(
    navController: NavHostController,
    isNavigationInProgress: MutableState<Boolean>,
    workoutListViewModel: WorkoutViewModel
) {
    MainScreenScaffold("Workouts", navController, isNavigationInProgress) { innerPadding ->
        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(
                PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    start = 4.dp + innerPadding.calculateStartPadding(LayoutDirection.Rtl),
                    end = 4.dp + innerPadding.calculateEndPadding(LayoutDirection.Rtl),
                    bottom = 0.dp
                )
            )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //Filter buttons
            WorkoutSegmentButton(
                modifier = buttonModifier,
                navController = navController,
                selectedOptionIndex = 1,
                isBlocked = isNavigationInProgress,
                workoutListViewModel = workoutListViewModel
            )

            val workoutState by workoutListViewModel.state.collectAsState()

            if (workoutState.isLoading && workoutState.workoutList.isEmpty()) { //Don't show loader if retrieved from cache...
                LoadingWheel(innerPadding = innerPadding)
            } else {
                WorkoutList(
                    innerPadding = innerPadding,
                    navController = navController,
                    workoutList = workoutState.workoutList
                )
            }
        }
    }
}
