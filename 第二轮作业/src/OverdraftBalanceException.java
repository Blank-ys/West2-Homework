/**
 * 余额不足异常
 */
public class OverdraftBalanceException extends RuntimeException{
    public OverdraftBalanceException() {
    }

    public OverdraftBalanceException(String message) {
        super(message);
    }
}
