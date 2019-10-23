package grow.demo.routine.repository;

import grow.demo.routine.domain.routine.RoutineCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineCollectionRepository extends JpaRepository<RoutineCategory, Long> {
}
