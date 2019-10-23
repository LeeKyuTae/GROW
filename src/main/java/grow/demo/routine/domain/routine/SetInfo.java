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
public class SetInfo {

    @Id
    @GeneratedValue
    @Column(name = "set_id")
    private Long setId;

    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @Column(name = "reps", nullable = false)
    private Integer reps;

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



    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

        if(this.getSetId() == null && ((SetInfo) obj).getSetId() != null)
            return false;


        if(((SetInfo) obj).getSetId() == null)
            return false;

        if(this.getSetId().equals(((SetInfo) obj).getSetId()))
            return true;

        return false;
    }


}
