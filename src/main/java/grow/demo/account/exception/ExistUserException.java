package grow.demo.account.exception;

public class ExistUserException extends RuntimeException {

    public ExistUserException() {
        super("이미 만들어진 계정입니다.");
    }
}
