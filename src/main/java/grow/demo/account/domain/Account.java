package grow.demo.account.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Long kakaoid;
}
