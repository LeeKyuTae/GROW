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
    @Column(name = "exercise_partials")
    private Set<ExercisePartial> exercisePartials;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "exercise_motions")
    private Set<ExerciseMotion> exerciseMotions;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "exercise_tools")
    private ExerciseTool exerciseTool;


    @ManyToMany(mappedBy = "exerciseList")
    private List<Routine> routineList ;


    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;



        if(this.getClass() != obj.getClass())
            return false;


        if(this.getExerciseId() == null && ((Exercise) obj).getExerciseId() != null)
            return false;


        if(((Exercise) obj).getExerciseId() == null)
            return false;


        if(this.getExerciseId().equals(((Exercise) obj).getExerciseId()))
            return true;

        return false;
    }
}
