package grow.demo.routine.domain.routine;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutineSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id")
    private Long setId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "routine_details_id")
    private Long routineDetailsId;

    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "lap", nullable = false)
    private Integer Lap;

    @Column(name = "repeat", nullable = false)
    private Integer Repeat;

    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "rest_time", nullable = true)
    private Integer restTime;
}
