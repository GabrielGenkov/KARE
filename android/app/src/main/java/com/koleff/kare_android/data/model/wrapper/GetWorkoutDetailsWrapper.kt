package com.koleff.kare_android.data.model.wrapper

import com.koleff.kare_android.data.model.response.GetWorkoutDetailsResponse

class GetWorkoutDetailsWrapper(getWorkoutDetailsResponse: GetWorkoutDetailsResponse) :
    ServerResponseData(getWorkoutDetailsResponse) {
    val workoutDetails = getWorkoutDetailsResponse.workoutDetails
}