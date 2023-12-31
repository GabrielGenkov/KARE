package com.koleff.kare_android.ui.compose

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.koleff.kare_android.data.MainScreen
import com.koleff.kare_android.data.model.event.OnWorkoutScreenSwitchEvent
import com.koleff.kare_android.ui.compose.navigation.blockNavigationButtons
import com.koleff.kare_android.ui.view_model.WorkoutViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutSegmentButton(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedOptionIndex: Int,
    isBlocked: MutableState<Boolean>,
    workoutListViewModel: WorkoutViewModel
) {
    var selectedIndex by remember { mutableStateOf(selectedOptionIndex) }
    val options = listOf("MyWorkout", "Workouts")

    LaunchedEffect(key1 = isBlocked.value) {
        Log.d(
            "Navigation LaunchedEffect",
            "Is navigation in progress: ${isBlocked.value}"
        )

        if (isBlocked.value) {
            blockNavigationButtons(isBlocked)
        }
    }

    SingleChoiceSegmentedButtonRow(modifier) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                onClick = {
                    if (isBlocked.value) return@SegmentedButton

                    //If the same option is selected
                    selectedIndex = if (selectedIndex == index) {
                        return@SegmentedButton
                    } else {
                        index
                    }

                    //Navigation
                    when (selectedIndex) {
                        0 -> {
                            navController.navigate(MainScreen.MyWorkout.route).also {
                                Log.d(
                                    "Navigation",
                                    "Updated isBlocked to true"
                                )

                                isBlocked.value = true

                                //Update view model
                                workoutListViewModel.onEvent(OnWorkoutScreenSwitchEvent.SelectedWorkout)
                            }
                        }

                        1 -> {
                            navController.navigate(MainScreen.Workouts.route).also {
                                Log.d(
                                    "Navigation",
                                    "Updated isBlocked to true"
                                )

                                isBlocked.value = true

                                //Update view model
                                workoutListViewModel.onEvent(OnWorkoutScreenSwitchEvent.AllWorkouts)
                            }
                        }
                    }
                },
                selected = index == selectedIndex
            ) {
                Text(label)
            }
        }
    }
}