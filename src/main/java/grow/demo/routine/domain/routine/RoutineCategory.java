package grow.demo.routine.domain.routine;


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
public class RoutineCategory {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category_type")
    private RoutineCategoryType collectionType;

    @OneToMany(mappedBy ="routineCategory", cascade = CascadeType.ALL)
    List<Routine> routineList = new ArrayList<>();


    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;


        if(this.getClass() != obj.getClass())
            return false;


        if(this.getCategoryId() == null && ((RoutineCategory) obj).getCategoryId() != null)
            return false;


        if(((RoutineCategory) obj).getCategoryId() == null)
            return false;


        if(this.getCategoryId().equals(((RoutineCategory) obj).getCategoryId()))
            return true;

        return false;
    }
}
