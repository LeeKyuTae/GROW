package grow.demo.account.exception;

public class ExistRoleException extends RuntimeException{

    public ExistRoleException() {
        super("이미 권한을 부여받았습니다.");
    }
}
