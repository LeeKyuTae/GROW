package grow.demo.routine.domain.routine;


import grow.demo.routine.domain.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Routine {

    @Id
    @GeneratedValue
    @Column(name = "routine_id")
    private Long routineId;

    private String routineName;

    @ManyToOne
    @JoinColumn(name = "routine_collection_id")
    private RoutineCollection routineCollection;

    @ManyToMany
    @JoinTable(name = "routine_exercise",
                joinColumns = @JoinColumn(name = "routine_id"),
                inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private List<Exercise> exerciseList = new ArrayList<>();




}
