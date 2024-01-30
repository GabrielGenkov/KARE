package io.kare.backend.payload.response;

import java.time.LocalDateTime;

public record GetCompletedWorkoutResponse(String id, String workoutName, LocalDateTime completionDate) {
}
