package grow.demo.routine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistRoutineException extends RuntimeException {

    public ExistRoutineException() {
        super("해당 루틴은 이미 존재합니다.");
    }

    public ExistRoutineException(String routineName) {
        super( routineName + "는 이미 존재합니다.");
    }

}
