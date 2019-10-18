package grow.demo.routine.dto;

import grow.demo.routine.domain.exercise.Exercise;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetDto {


    private Integer laps;

    private Integer repeats;

    private Float weight;

    private Integer restTime;

    Exercise exercise;

}
