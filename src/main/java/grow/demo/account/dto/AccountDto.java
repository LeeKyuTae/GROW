package grow.demo.account.dto;


import grow.demo.routine.dto.RoutineCategoryDto;
import lombok.*;

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
        private String birth;
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
        private String birth;
        private List<RoutineCategoryDto.CategoryResponse> routineCollectionList;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class AccountInfoUpdateRequest{
        private String userEmail;
        private String userName;
        private Float weight;
        private Float height;
        private String gender;
        private String birth;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class WeightUpdateRequest{
        private Float weight;
    }


}
