package grow.demo.account.domain;


import grow.demo.routine.domain.routine.SetInfo;
import grow.demo.routine.domain.routine.RoutineCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private String userEmail;

    @Column(name = "account_name",unique = true , nullable = false)
    private String userName;

    @Column(name = "account_weight", nullable = false)
    private Float weight;

    @Column(name = "account_height", nullable = false)
    private Float height;

    @Column(name = "account_gender", nullable = false)
    private String gender;

    @Column(name = "account_birth", nullable = false)
    private String birth;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_roles")
    private Set<AccountRole> accountRoles;


    @Column(name = "kakao_access_id", nullable = false)
    private Long kakaoId;


    @OneToMany(mappedBy = "account")
    private List<SetInfo> setInfos = new ArrayList<>();



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="account_routine_category",
                joinColumns = @JoinColumn(name = "account_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<RoutineCategory> routineCategoryList = new ArrayList<>();
}
