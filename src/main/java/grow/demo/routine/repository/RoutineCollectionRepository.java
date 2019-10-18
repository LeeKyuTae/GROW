package grow.demo.routine.repository;

import grow.demo.routine.domain.routine.RoutineCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineCollectionRepository extends JpaRepository<RoutineCollection, Long> {
}
