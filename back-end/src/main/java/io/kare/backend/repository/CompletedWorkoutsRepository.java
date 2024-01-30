package io.kare.backend.repository;

import io.kare.backend.entity.CompletedWorkoutEntity;
import io.kare.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CompletedWorkoutsRepository extends JpaRepository<CompletedWorkoutEntity, String> {
    Optional<List<CompletedWorkoutEntity>> findAllByUser(UserEntity user);
}
