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
public class RoutineCollection {

    @Id
    @GeneratedValue
    @Column(name = "routine_collection_id")
    private Long collectionId;

    @Column(name = "routine_collection_name")
    private String routineName;


    @OneToMany(mappedBy ="routineCollection", cascade = CascadeType.ALL)
    List<Routine> routineList = new ArrayList<>();
}
