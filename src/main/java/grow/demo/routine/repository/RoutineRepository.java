package grow.demo.routine.repository;

import grow.demo.routine.domain.routine.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long > {
    List<Routine> findByRoutineCategory_CategoryId(Long categoryId);
}
