package grow.demo.routine.domain.exercise;


import grow.demo.routine.domain.routine.Routine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue
    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "exercise_name")
    private String exerciseName;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "exercise_partials", nullable = false)
    private Set<ExercisePartial> exercisePartials;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "exercise_motions", nullable = false)
    private Set<ExerciseMotion> exerciseMotions;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "exercise_tools", nullable = false)
    private Set<ExerciseTool> exerciseTools;


    @ManyToMany(mappedBy = "exerciseList")
    private List<Routine> routineList = new ArrayList<>();
}
