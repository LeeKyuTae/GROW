package grow.demo.routine.dto;

import grow.demo.routine.domain.exercise.Exercise;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public class SetInfoDto {


    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class SetInfoRequest {

        @NotNull
        private Long routineId;

        @NotNull
        private Long exerciseId;
    }


    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    //List형태로
    public static class SetInfoResponse {

        private Long routineId;

        private Long exerciseId;

       private List<SetDetail> detailList = new ArrayList<>();
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class SetDetail{
        private Long setId;

        private Integer setNumber;

        private Integer reps;

        private Float weight;

        private Integer restTime;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RegisterRequest {

        @NotNull @Min(value = 1)
        private Integer setNumber;

        @NotNull @Min(value = 1)
        private Integer reps;

        @NotNull @Min(value = 0)
        private Float weight;

        @NotNull
        private Integer restTime;

        @NotNull
        private Long routineId;

        @NotNull
        private Long exerciseId;

    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class UpdateRequest {
        @NotNull
        private Long setId;

        @NotNull @Min(value = 1)
        private Integer reps;

        @NotNull @Min(value = 1)
        private Float weight;

        @NotNull
        private Integer restTime;
    }


    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RegisterResponse {
        private Long setId;

        private Long accountId;

        private Integer setNumber;

        private Integer reps;

        private Float weight;

        private Integer restTime;

        private Long routineId;

        private Long exerciseId;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class DeleteResponse{
        private Long routineId;

        private Long accountId;

        private Long exerciseId;
    }

}
