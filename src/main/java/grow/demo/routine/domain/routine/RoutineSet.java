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
    @GeneratedValue
    @Column(name = "set_id")
    private Long setId;

    @Column(name = "laps", nullable = false)
    private Integer laps;

    @Column(name = "repeats", nullable = false)
    private Integer repeats;

    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "rest_time", nullable = true)
    private Integer restTime;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    Routine routine;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;
}
