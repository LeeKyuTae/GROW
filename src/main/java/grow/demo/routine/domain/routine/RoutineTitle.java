package grow.demo.routine.domain.routine;


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
public class RoutineTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_title_id")
    private Long routineTitleId;

    @Column(name = "routine_title_name")
    private String RoutineName;


    @OneToMany
    @JoinColumn(name = "routine_details_id")
    List<RoutineDetails> routineDetailsList;
}
