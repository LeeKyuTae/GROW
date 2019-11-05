package grow.demo.account.dto;


import grow.demo.routine.dto.RoutineCategoryDto;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;


public class AccountDto {

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class AccountRequest{
        private Long accountId;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class AccountRequest_kakaoId{
        private Long kakaoId;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class AccountResponse{
        private Long accountId;
        private String userEmail;
        private String userName;
        private Float weight;
        private Float height;
        private String gender;
        private Integer age;
       // private String birth;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class SignInResponse{
        private Boolean isSignIn;
        private String jwt;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class AccountFullResponse{
        private Long accountId;
        private String userEmail;
        private String userName;
        private Float weight;
        private Float height;
        private String gender;
        private Integer age;
        //private String birth;
        private List<RoutineCategoryDto.CategoryResponse> routineCollectionList;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class AccountInfoUpdateRequest{

        private String userEmail;

        @NotBlank
        private String userName;

        @Min(value = 1)
        private Float weight;

        @Min(value = 1)
        private Float height;

        @Min(value = 1)
        private Integer age;

        @NotBlank
        private String gender;


        private String birth;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class WeightUpdateRequest{
        @Min(value = 1)
        private Float weight;
    }


}
