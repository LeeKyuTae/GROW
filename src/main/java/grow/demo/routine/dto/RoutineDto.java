package grow.demo.routine.dto;


import lombok.*;


public class RoutineDto {

    @Getter
    @Builder
    @Setter
    public static class RoutineInfoRequest {

        private Long category_id;
    }


    @Getter
    @Builder
    @Setter
    public static class RoutineInfoResponse {
        private Long routineId;
        private String routineName;
    }

}
