package grow.demo.account.dto;


import grow.demo.routine.domain.routine.RoutineCollection;
import lombok.*;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
public class AccountDto {
    private String userEmail;
    private String userName;
    private Float weight;
    private Float height;
    private String gender;
    private String birth;
    private List<RoutineCollection> routineCollectionList;
}
