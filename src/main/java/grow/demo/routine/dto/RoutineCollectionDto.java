package grow.demo.routine.dto;


import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCollectionRole;
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

   private Long id;

   private RoutineCollectionRole role;


}
