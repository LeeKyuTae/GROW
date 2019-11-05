package grow.demo.account.domain;


import grow.demo.account.dto.AccountDto;
import grow.demo.account.exception.ExistRoleException;
import grow.demo.record.domain.Records;
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

    @Column(name = "account_email",unique = true)
    private String userEmail;

    @Column(name = "account_name",unique = true)
    private String userName;

    @Column(name = "account_weight")
    private Float weight;

    @Column(name = "account_height")
    private Float height;

    @Column(name = "account_gender")
    private String gender;

    @Column(name = "account_age")
    private Integer age;

    @Column(name = "account_birth")
    private String birth;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_roles")
    private Set<AccountRole> accountRoles;


    @Column(name = "kakao_access_id")
    private Long kakaoId;


    @OneToMany(mappedBy = "account", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<SetInfo> setInfos = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Records> recordsList = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="account_routine_category",
                joinColumns = @JoinColumn(name = "account_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<RoutineCategory> routineCategoryList = new ArrayList<>();

    public void updateAccountInfo(AccountDto.AccountInfoUpdateRequest request){
        this.birth = request.getBirth();
        this.gender = request.getGender();
        this.height = request.getHeight();
        this.weight = request.getWeight();
        this.userEmail = request.getUserEmail();
        this.userName = request.getUserName();
    }

    public void registerCategoryList(List<RoutineCategory> categories){
        if(this.routineCategoryList == null)
            this.routineCategoryList = new ArrayList<>();

        for(RoutineCategory category : categories)
            this.routineCategoryList.add(category);
    }

    public void updateWeight(AccountDto.WeightUpdateRequest request){
        this.weight = request.getWeight();
    }

    public void empowerAdminRole(){
        if(this.accountRoles.contains(AccountRole.ADMIN))
            throw new ExistRoleException();
        else
            this.accountRoles.add(AccountRole.ADMIN);
    }




    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

        if(this.getId() == null && ((Account) obj).getId() != null)
            return false;


        if(((Account) obj).getId() == null)
            return false;

        if(this.getId().equals(((Account) obj).getId()))
            return true;

        return false;
    }
}
