package grow.demo.routine.dto;


import grow.demo.routine.domain.exercise.ExerciseMotion;
import grow.demo.routine.domain.exercise.ExercisePartial;
import grow.demo.routine.domain.exercise.ExerciseTool;
import lombok.*;

import java.util.Set;

@Getter
@Builder
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ExerciseDto {

    private Long exerciseId;

    private String exerciseName;

    private Set<ExercisePartial> exercisePartials;

    private Set<ExerciseMotion> exerciseMotions;

    private Set<ExerciseTool> exerciseTools;

}
