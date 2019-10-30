package grow.demo.config;


import grow.demo.account.exception.ExistRoleException;
import grow.demo.account.exception.ExistUserException;
import grow.demo.routine.exception.*;
import javassist.NotFoundException;
import org.apache.http.client.ClientProtocolException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
public class GloballControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity handleNotFountException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }


    // 이게 있어야 하는건지 의문임
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("원인 불명 에러" + "\n" + e.getStackTrace());
    }


    @ExceptionHandler({ClientProtocolException.class})
    public ResponseEntity handleClientProtocolException(ClientProtocolException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity handleIOException(IOException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }



    //==================================================================================================================


    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getStackTrace());
    }

    @ExceptionHandler({ExistRoutineException.class})
    public ResponseEntity handleExistRoutineException(ExistRoutineException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }

    @ExceptionHandler({ExistExerciseException.class})
    public ResponseEntity handleExistExerciseException(ExistExerciseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }

    @ExceptionHandler({NotExistExerciseException.class})
    public ResponseEntity handleNotExistExerciseException(NotExistExerciseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }

    @ExceptionHandler({NotModifyCategoryException.class})
    public ResponseEntity handleNotModifyCategoryException(NotModifyCategoryException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }

    @ExceptionHandler({ExistUserException.class})
    public ResponseEntity handleExistUserException(ExistUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }

    @ExceptionHandler({ExistCategoryException.class})
    public ResponseEntity handleExistCategoryException(ExistCategoryException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }

    @ExceptionHandler({ExistRoleException.class})
    public ResponseEntity handleExistRoleException(ExistRoleException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
    }

}
