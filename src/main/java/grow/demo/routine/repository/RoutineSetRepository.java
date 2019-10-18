package grow.demo.routine.repository;

import grow.demo.routine.domain.routine.RoutineSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineSetRepository extends JpaRepository<RoutineSet, Long> {
}
