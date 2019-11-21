package grow.demo.record.domain;


import grow.demo.account.domain.Account;
import grow.demo.routine.domain.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestRecord {

    @Id
    @GeneratedValue
    @Column(name = "1rm_id")
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime exerciseDate;

    private Float weight;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
