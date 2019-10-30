package grow.demo.routine.dto;

import grow.demo.routine.domain.exercise.Exercise;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


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
    public static class UpdateRequest {
        private Long setId;

        private Integer reps;

        private Float weight;

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

}
