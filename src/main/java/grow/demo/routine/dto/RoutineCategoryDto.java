package grow.demo.routine.dto;


import grow.demo.routine.domain.routine.RoutineCategoryType;
import lombok.*;

import java.util.List;


public class RoutineCategoryDto {

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class CategoryRequest{
        private Long categoryId;
    }


    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class MyCategoryRequest {

        //user ID
        private RoutineCategoryType categoryType;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    //줄때는 이걸 List로 묶어서 보내주기
    public static class CategoryResponse {

        private Long categoryId;

        private RoutineCategoryType categoryType;

        private String categoryName;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RegisterRequest {
        private RoutineCategoryType categoryType;

        private String categoryName;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RegisterRoutineRequest {
        private Long categoryId;

        private Long routineId;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class FullCategoryResponse{
        private Long categoryId;

        private RoutineCategoryType categoryType;

        private String categoryName;

        private List<RoutineDto.RoutineInfoResponse> routineList;
    }



}
