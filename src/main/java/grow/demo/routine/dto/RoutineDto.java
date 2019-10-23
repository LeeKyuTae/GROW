package grow.demo.routine.dto;


import lombok.*;


public class RoutineDto {

    @Getter
    @Builder
    @Setter
    public static class RoutineInfoRequest {
        private Long category_id;
    }

    @Getter
    @Builder
    @Setter
    public static class RoutineInfoResponse {
        private Long routineId;
        private String routineName;
    }


    @Getter
    @Builder
    @Setter
    public static class RegisterRequest {
        private String routineName;
    }

    @Getter
    @Builder
    @Setter
    public static class ExerciseRequest {
        private Long routineId;

        private Long exerciseId;
    }


}
