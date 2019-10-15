package grow.demo.account.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class AccountInfoDto {
    private String useremail;
    private String username;
    private Float weight;
    private Float height;
    private String gender;
    private String birth;
}
