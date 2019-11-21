package grow.demo.record.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BestRecordDto {


    @Getter
    @Builder
    @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class RegisterRequest{

        @NotNull
        private Long exerciseId;

        @Min(value = 0)
        private Float weight;
    }

    @Getter
    @Builder
    @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class InfoRequest{

        @NotNull
        private Long exerciseId;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RecordResponse{
        private Long recordId;

        private LocalDateTime exerciseDate;

        private Long exerciseId;

        private String exerciseName;

        private Float weight;
    }
}
