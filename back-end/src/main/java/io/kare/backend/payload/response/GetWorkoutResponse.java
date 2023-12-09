package io.kare.backend.payload.response;

import io.kare.backend.payload.data.ExercisePayload;

import java.util.List;

public record GetWorkoutResponse(String id, String name, String description, List<ExercisePayload> exercises) {
}