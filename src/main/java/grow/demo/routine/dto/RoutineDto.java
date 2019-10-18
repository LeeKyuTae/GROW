package grow.demo.routine.dto;


import grow.demo.routine.domain.exercise.Exercise;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineDto {
    private Long routineId;

    private String routineName;

    private List<Exercise> exerciseList ;
}
