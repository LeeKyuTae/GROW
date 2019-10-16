package grow.demo.routine.domain.routine;


import grow.demo.routine.domain.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoutineDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_details_id")
    private Long routineDetailsId;

    @OneToMany
    @JoinColumn(name = "exercise_id")
    List<Exercise> exerciseList;

    @OneToMany
    @JoinColumn(name = "set_id")
    List<RoutineSet> routineSetList;
}
