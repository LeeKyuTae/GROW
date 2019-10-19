package grow.demo.routine.exception;

public class ExistExerciseException extends RuntimeException {

    public ExistExerciseException() {
        super("해당 운동은 이미 존재합니다.");
    }



    public ExistExerciseException(String exerciseName) {
        super( exerciseName + "는 이미 존재합니다.");
    }

    public ExistExerciseException(String exerciseName, String routineName) {
        super( exerciseName + "는 이미 " + routineName + "에 존재합니다.");
    }
}
