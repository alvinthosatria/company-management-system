public class ExInvalidLeave extends Exception {
    public ExInvalidLeave(int currentBalance, int remaining) {
        super("The annual leave is invalid.\n" + 
        "The current balance is " + currentBalance+" days only.\n" + 
        "The employee has not taken any block leave yet.\n" + 
        "The employee can take at most "+ remaining+ " days of non-block annual leave\n" +
        "because of the need to reserve 7 days for a block leave.");
    }

    public ExInvalidLeave(String message) {
        super(message);
    }
}
