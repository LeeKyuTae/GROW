package grow.demo.routine.dto;


import grow.demo.routine.domain.routine.RoutineCategoryType;
import lombok.*;


public class RoutineCategoryDto {


    @Getter
    @Builder
    @Setter
    public static class MyCategoryRequest {

        //user ID
        private RoutineCategoryType routineCategoryType;
    }



    @Getter
    @Builder
    @Setter
    //줄때는 이걸 List로 묶어서 보내주기
    public static class CategoryResponse {

        private Long categoryId;

        private RoutineCategoryType routineCategoryType;

        private String categoryName;
    }





}
