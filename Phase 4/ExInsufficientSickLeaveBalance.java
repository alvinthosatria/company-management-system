public class ExInsufficientSickLeaveBalance extends Exception {
    public ExInsufficientSickLeaveBalance(int balance) {
        super("Insufficient balance of sick leaves. " + balance + " days left only!");
    }

    public ExInsufficientSickLeaveBalance(String message) {
        super(message);
    }
}
