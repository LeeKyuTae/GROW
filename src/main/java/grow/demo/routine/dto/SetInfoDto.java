package grow.demo.routine.dto;

import grow.demo.routine.domain.exercise.Exercise;
import lombok.*;


public class SetInfoDto {


    @Getter
    @Builder
    @Setter
    public static class SetInfoRequest {

        //userId

        private Long routineId;

        private Long exerciseId;
    }


    @Getter
    @Builder
    @Setter
    //List형태로
    public static class SetInfoResponse {

        private Integer setNumber;

        private Integer reps;

        private Float weight;

        private Integer restTime;
    }

}
