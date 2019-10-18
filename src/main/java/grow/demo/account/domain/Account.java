package grow.demo.account.domain;


import grow.demo.routine.domain.routine.RoutineSet;
import grow.demo.routine.domain.routine.RoutineCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column(name = "kakao_access_id", nullable = false)
    private Long kakaoId;


    @OneToMany(mappedBy = "account")
    private List<RoutineSet> routineSets = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="account_routinecollection",
                joinColumns = @JoinColumn(name = "account_id"),
                inverseJoinColumns = @JoinColumn(name = "routine_collection_id"))
    private List<RoutineCollection> routineCollectionList = new ArrayList<>();
}
