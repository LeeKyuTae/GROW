package grow.demo.routine.exception;

public class ExistRoutineException extends RuntimeException {

    public ExistRoutineException() {
        super("해당 루틴은 이미 존재합니다.");
    }

    public ExistRoutineException(String routineName) {
        super( routineName + "는 이미 존재합니다.");
    }

}
