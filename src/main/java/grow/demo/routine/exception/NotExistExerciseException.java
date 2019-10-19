package grow.demo.routine.exception;

public class NotExistExerciseException extends RuntimeException {

    public NotExistExerciseException() {
        super("해당 운동은 루틴에 존재하지 않습니다.");
    }



    public NotExistExerciseException(String msg) {
        super( msg + "는 이미 루틴에 존재하지 않습니다.");
    }
}
