package grow.demo.routine.domain.routine;


import grow.demo.account.domain.Account;
import grow.demo.routine.domain.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoutineSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id")
    private Long setId;

    @OneToOne
    @JoinColumn(name = "exercise_id")
    Exercise exercise;

    @Column(name = "lap", nullable = false)
    private Integer Lap;

    @Column(name = "repeat", nullable = false)
    private Integer Repeat;

    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "rest_time", nullable = true)
    private Integer restTime;
}
