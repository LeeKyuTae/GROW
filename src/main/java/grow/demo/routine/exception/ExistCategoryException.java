package grow.demo.routine.exception;

public class ExistCategoryException extends RuntimeException {

    public ExistCategoryException() {
        super("해당 카테고리는 이미 존재합니다.");
    }
}
