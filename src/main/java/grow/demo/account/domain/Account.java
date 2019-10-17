package grow.demo.account.domain;


import grow.demo.routine.domain.routine.RoutineSet;
import grow.demo.routine.domain.routine.RoutineTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_email",unique = true, nullable = false)
    private String useremail;

    @Column(name = "account_name",unique = true , nullable = false)
    private String username;

    @Column(name = "account_weight", nullable = false)
    private Float weight;

    @Column(name = "account_height", nullable = false)
    private Float height;

    @Column(name = "account_gender", nullable = false)
    private String gender;

    @Column(name = "account_birth", nullable = false)
    private String birth;

    @Column(name = "kakao_access_id", nullable = false)
    private Long kakaoId;


    @OneToMany
    @JoinColumn(name = "set_id")
    List<RoutineSet> routineSetList;

    @OneToMany
    @JoinColumn(name = "routine_title_id")
    List<RoutineTitle> routineTitleList;
}
