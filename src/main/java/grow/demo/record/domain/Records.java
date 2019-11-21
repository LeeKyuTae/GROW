package grow.demo.record.domain;


import grow.demo.account.domain.Account;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.SetInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Records {

    @Id @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime exerciseDate;

    private Float userWeight;

    private Integer setNumber;

    private Integer reps;

    private Float toolWeight;

    private Integer restTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

}
