package grow.demo.routine.repository;

import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface RoutineCategoryRepository extends JpaRepository<RoutineCategory, Long> {
    List<RoutineCategory> findAllByCategoryType(RoutineCategoryType categoryType);
}
