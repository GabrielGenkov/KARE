package io.kare.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CompletedWorkoutHistory")
public class CompletedWorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserEntity.ID_COLUMN, nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = WorkoutEntity.ID_COLUMN, nullable = false)
    private WorkoutEntity workout;

    @Column(name = "completionDate", nullable = false)
    private LocalDateTime completionDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setWorkout(WorkoutEntity workout) {this.workout = workout;}

    public WorkoutEntity getWorkout(){return this.workout;}

    public String getWorkoutName() {
        return workout.getName();
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }
}
