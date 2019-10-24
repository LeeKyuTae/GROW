package grow.demo.routine.dto;


import lombok.*;

import java.util.List;


public class RoutineDto {

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RoutineListInfoRequest {
        private Long category_id;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RoutineListInfoResponse {
        private List<RoutineInfoResponse> responses;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RoutineInfoRequest {
        private Long routine_id;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RoutineInfoResponse {
        private Long routineId;
        private String routineName;
    }


    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RegisterRequest {
        private String routineName;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class ExerciseRequest {
        private Long routineId;

        private Long exerciseId;
    }


}
