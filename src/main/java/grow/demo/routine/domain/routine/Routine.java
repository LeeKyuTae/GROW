package grow.demo.routine.domain.routine;


import grow.demo.routine.domain.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Routine {

    @Id
    @GeneratedValue
    @Column(name = "routine_id")
    private Long routineId;


    @Column(name = "routine_name")
    private String routineName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private RoutineCategory routineCategory;

    @ManyToMany
    @JoinTable(name = "routine_exercise",
                joinColumns = @JoinColumn(name = "routine_id"),
                inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private List<Exercise> exerciseList = new ArrayList<>();


    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;


        if(this.getClass() != obj.getClass())
            return false;


        if(this.getRoutineId() == null && ((Routine) obj).getRoutineId() != null)
            return false;


        if(((Routine) obj).getRoutineId() == null)
            return false;


        if(this.getRoutineId().equals(((Routine) obj).getRoutineId()))
            return true;

        return false;
    }

    public boolean registerCategory(RoutineCategory routineCategory){
        if(this.routineCategory != null) {
           return false;
        }
        this.routineCategory = routineCategory;
        return true;
    }
}
