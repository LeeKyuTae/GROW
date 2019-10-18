package grow.demo.routine.repository;

import grow.demo.routine.domain.routine.RoutineSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineSetRepository extends JpaRepository<RoutineSet, Long> {

    public List<RoutineSet> findRoutineSetByAccount_IdAndRoutine_RoutineIdAndExercise_ExerciseId(Long account_id, Long routine_id, Long set_id);
    public List<RoutineSet> findRoutineSetByAccount_IdAndRoutine_RoutineIdAndExercise_ExerciseIdAAndLaps(Long account_id, Long routine_id, Long set_id, int lap);
}
