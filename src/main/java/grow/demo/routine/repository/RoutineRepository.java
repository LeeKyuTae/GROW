package grow.demo.routine.repository;

import grow.demo.routine.domain.routine.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long > {
}
