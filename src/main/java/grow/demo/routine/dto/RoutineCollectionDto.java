package grow.demo.routine.dto;


import grow.demo.routine.domain.routine.Routine;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineCollectionDto {

    private String routineName;

    List<Routine> routineList ;
}
