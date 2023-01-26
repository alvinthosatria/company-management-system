public class ExInsufficientAnnualLeaveBalance extends Exception {

    public ExInsufficientAnnualLeaveBalance(int balance) {
        super("Insufficient balance of annual leaves. " + balance + " days left only!");
    }

    public ExInsufficientAnnualLeaveBalance(String message) {
        super(message);
    }
}
