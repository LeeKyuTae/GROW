package grow.demo.routine.domain.routine;


import grow.demo.account.domain.Account;
import grow.demo.record.domain.Records;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.dto.SetInfoDto;
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
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public void updateSetInfo(SetInfoDto.UpdateRequest request){
        this.reps = request.getReps();
        this.restTime = request.getRestTime();
        this.weight = request.getWeight();
    }

    public SetInfo reduceSetNumber(){
        if(this.setNumber > 2)
            this.setNumber -= 1;
        return this;
    }


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
