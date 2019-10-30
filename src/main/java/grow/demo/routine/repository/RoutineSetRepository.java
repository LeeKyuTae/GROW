package grow.demo.routine.repository;

import grow.demo.routine.domain.routine.SetInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineSetRepository extends JpaRepository<SetInfo, Long> {

    public List<SetInfo> findAllByAccount_IdAndRoutine_RoutineIdAndExercise_ExerciseId(Long account_id, Long routine_id, Long exercise_id);
    public List<SetInfo> findAllByAccount_IdAndRoutine_RoutineI(Long account_id, Long routine_id);
}
