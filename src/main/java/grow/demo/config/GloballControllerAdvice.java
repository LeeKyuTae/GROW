package grow.demo.config;


import grow.demo.account.exception.ExistUserException;
import grow.demo.routine.exception.*;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GloballControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity handleNotFountException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    // 이게 있어야 하는건지 의문임
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("원인 불명 에러");
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler({ExistRoutineException.class})
    public ResponseEntity handleExistRoutineException(ExistRoutineException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ExistExerciseException.class})
    public ResponseEntity handleExistExerciseException(ExistExerciseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({NotExistExerciseException.class})
    public ResponseEntity handleNotExistExerciseException(NotExistExerciseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({NotModifyCategoryException.class})
    public ResponseEntity handleNotModifyCategoryException(NotModifyCategoryException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ExistUserException.class})
    public ResponseEntity handleExistUserException(ExistUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ExistCategoryException.class})
    public ResponseEntity handleExistCategoryException(ExistCategoryException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
