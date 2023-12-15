package com.koleff.kare_android.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.koleff.kare_android.common.MockupDataGenerator
import com.koleff.kare_android.data.MainScreen
import com.koleff.kare_android.data.model.dto.MuscleGroup
import com.koleff.kare_android.data.model.dto.WorkoutDto
import com.koleff.kare_android.ui.compose.banners.WorkoutBanner

@Composable
fun SearchWorkoutList(
    modifier: Modifier,
    workoutList: List<WorkoutDto>,
    exerciseId: Int,
    navController: NavHostController
) {
    LazyColumn(modifier = modifier) {
        items(workoutList.size) { currentWorkoutId ->
            val currentWorkout = workoutList[currentWorkoutId]
            WorkoutBanner(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                workout = currentWorkout,
            ) {
                //TODO: Add exercise to workout...
                //TODO: show dialog for successfully added workout...
                navController.navigate(MainScreen.Dashboard.route)
            }
        }
    }
}


@Preview
@Composable
fun SearchWorkoutListPreview() {
    val modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)

    val workoutList = MockupDataGenerator.generateWorkoutList()

    SearchWorkoutList(
        modifier = modifier,
        workoutList = workoutList,
        exerciseId = 1,
        navController = rememberNavController()
    )
}