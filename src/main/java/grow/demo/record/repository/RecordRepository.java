package grow.demo.record.repository;

import grow.demo.record.domain.Records;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Records, Long> {
    public List<Records> findAllByExerciseDateBetween(LocalDate firstTime, LocalDate lastTime);
    public List<Records> findAllByExercise_ExerciseId(Long exerciseId);
    public List<Records> findAllByAccount_Id(Long accountId);
}
