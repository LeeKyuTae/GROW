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
import java.io.PrintWriter;
import java.io.StringWriter;


@RestControllerAdvice
public class GloballControllerAdvice {

    public static String getPrintStackTrace(Exception e) {

        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity handleNotFountException(NotFoundException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    /*
    // 이게 있어야 하는건지 의문임
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("원인 불명 에러" + "\n" + e.getStackTrace());
    }

     */


    @ExceptionHandler({ClientProtocolException.class})
    public ResponseEntity handleClientProtocolException(ClientProtocolException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity handleIOException(IOException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }



    //==================================================================================================================


    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity handleUnauthorizedException(UnauthorizedException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler({ExistRoutineException.class})
    public ResponseEntity handleExistRoutineException(ExistRoutineException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ExistExerciseException.class})
    public ResponseEntity handleExistExerciseException(ExistExerciseException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({NotExistExerciseException.class})
    public ResponseEntity handleNotExistExerciseException(NotExistExerciseException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({NotModifyCategoryException.class})
    public ResponseEntity handleNotModifyCategoryException(NotModifyCategoryException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ExistUserException.class})
    public ResponseEntity handleExistUserException(ExistUserException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ExistCategoryException.class})
    public ResponseEntity handleExistCategoryException(ExistCategoryException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ExistRoleException.class})
    public ResponseEntity handleExistRoleException(ExistRoleException e) {
        System.out.println(getPrintStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
