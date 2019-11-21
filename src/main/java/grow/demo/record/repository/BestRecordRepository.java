package grow.demo.record.repository;

import grow.demo.record.domain.BestRecord;
import grow.demo.routine.domain.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BestRecordRepository extends JpaRepository<BestRecord, Long> {
    public List<BestRecord>  findAllByExercise_ExerciseIdOrderByExerciseDate(Long exerciseId);
    public List<BestRecord> findAllByAccount_Id(Long accountId);
}
