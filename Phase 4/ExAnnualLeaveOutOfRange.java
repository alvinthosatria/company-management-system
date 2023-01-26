public class ExAnnualLeaveOutOfRange extends Exception {
    public ExAnnualLeaveOutOfRange() {
        super("Out of range (Entitled Annual Leaves should be within 0-300)!");
    }

    public ExAnnualLeaveOutOfRange(String message) {
        super(message);
    }
}
