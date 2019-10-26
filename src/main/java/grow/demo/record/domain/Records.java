package grow.demo.record.domain;


import grow.demo.account.domain.Account;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.routine.SetInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate exerciseDate;

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



}