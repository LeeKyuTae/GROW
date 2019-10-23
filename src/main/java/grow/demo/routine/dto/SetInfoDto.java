package grow.demo.routine.dto;

import grow.demo.routine.domain.exercise.Exercise;
import lombok.*;


public class SetInfoDto {


    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class SetInfoRequest {

        //userId

        private Long routineId;

        private Long exerciseId;
    }


    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    //List형태로
    public static class SetInfoResponse {

        private Integer setNumber;

        private Integer reps;

        private Float weight;

        private Integer restTime;
    }

    @Getter
    @Builder
    @Setter @AllArgsConstructor @NoArgsConstructor
    public static class RegisterRequest {

        private Integer setNumber;

        private Integer reps;

        private Float weight;

        private Integer restTime;

        private Long routineId;

        private Long exerciseId;

    }

}
