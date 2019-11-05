package grow.demo.record.dto;

import lombok.*;

import java.time.LocalDate;

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

        private LocalDate exerciseDate;

        private Float userWeight;

        private Integer setNumber;

        private Integer reps;

        private Float toolWeight;

        private Integer restTime;
    }

}
