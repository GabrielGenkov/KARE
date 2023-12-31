package com.koleff.kare_android.data.model.dto

import com.squareup.moshi.Json

data class WorkoutDetailsDto(
    @field:Json(name = "id")
    val workoutId: Int = -1,
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "description")
    val description: String = "",
    @field:Json(name = "muscle_group")
    val muscleGroup: MuscleGroup = MuscleGroup.NONE,
    @field:Json(name = "exercises")
    val exercises: List<ExerciseDto> = emptyList(),
    @field:Json(name = "is_selected")
    val isSelected: Boolean = false
)