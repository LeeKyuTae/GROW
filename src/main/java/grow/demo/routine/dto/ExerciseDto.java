package grow.demo.routine.dto;


import grow.demo.routine.domain.exercise.ExerciseMotion;
import grow.demo.routine.domain.exercise.ExercisePartial;
import grow.demo.routine.domain.exercise.ExerciseTool;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ExerciseDto {
    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RoutineExerciseRequest {
        private Long routineId;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RoutineExerciseResponse {

        private List<ExerciseResponse> exerciseResponseList = new ArrayList<>();
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class ExerciseResponse {
        private Long exerciseId;

        private String exerciseName;

        private Set<ExercisePartial> exercisePartials;

        private Set<ExerciseMotion> exerciseMotions;

        private ExerciseTool exerciseTool;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RegisterRequest {

        private String exerciseName;
        private Set<ExercisePartial> exercisePartials;
        private Set<ExerciseMotion> exerciseMotions;
        private ExerciseTool exerciseTool;
    }

}
