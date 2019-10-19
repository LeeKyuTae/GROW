package grow.demo.routine.exception;

public class ExistExerciseException extends RuntimeException {

    public ExistExerciseException() {
        super("해당 운동은 이미 루틴에 존재합니다.");
    }



    public ExistExerciseException(String msg) {
        super( msg + "는 이미 루틴에 존재합니다.");
    }
}
