package io.kare.backend.payload.response;

import java.util.List;

public record GetCompletedWorkoutsResponse(List<GetCompletedWorkoutResponse> workouts) {
}
