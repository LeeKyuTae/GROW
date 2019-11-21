package grow.demo.routine.repository;

import grow.demo.routine.domain.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    public List<Exercise> findAllByExerciseNameLike(String exerciseName);

    public Boolean existsByExerciseName(String exerciseName);

    public Exercise findByExerciseName(String name);
}
