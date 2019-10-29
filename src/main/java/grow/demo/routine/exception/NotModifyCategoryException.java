package grow.demo.routine.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotModifyCategoryException extends RuntimeException {
    public NotModifyCategoryException() {
        super("루틴의 카테고리를 변경할 수 없습니다.");
    }
}
