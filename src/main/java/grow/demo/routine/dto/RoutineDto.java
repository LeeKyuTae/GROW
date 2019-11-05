package grow.demo.routine.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


public class RoutineDto {

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RoutineListInfoRequest {
        @NotBlank
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
        @NotNull
        private Long routineId;
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

        @NotNull
        private Long categoryId;

        @NotBlank
        private String routineName;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class ExerciseRequest {
        @NotNull
        private Long routineId;

        @NotNull
        private Long exerciseId;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class FullInfoResponse {
        private Long routineId;

        private String routineName;

        private List<ExerciseDto.ExerciseResponse> exerciseList;
    }


}
