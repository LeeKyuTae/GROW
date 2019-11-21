package grow.demo.record.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RecordsDto {

    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest{
        private Long accountId;

        private Long setId;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RecordResponse{
        private Long recordId;

        private LocalDateTime exerciseDate;

        private Float userWeight;

        private Integer setNumber;

        private Integer reps;

        private Float toolWeight;

        private Integer restTime;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class ExerciseResponse{
        private Long exerciseId;

        private String exerciseName;
    }

}
