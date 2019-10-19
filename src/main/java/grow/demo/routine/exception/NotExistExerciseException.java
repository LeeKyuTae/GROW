package grow.demo.routine.exception;

public class NotExistExerciseException extends RuntimeException {

    public NotExistExerciseException() {
        super("해당 운동은 존재하지 않는 운동 입니다.");
    }



    public NotExistExerciseException(String msg) {
        super( msg + "는 이미존재하지 않는 운동 입니다.");
    }

    public NotExistExerciseException(String exerciseName, String routineName) {
        super( exerciseName + "는 " +  routineName + "에 존재하지 않습니다.");
    }
}
