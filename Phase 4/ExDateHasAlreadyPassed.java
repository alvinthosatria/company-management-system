public class ExDateHasAlreadyPassed extends Exception {
    public ExDateHasAlreadyPassed(Day systemdate) {
        super("Wrong Date. Leave start date must not be earlier than the system date (" + systemdate.toString() + ")!");
    }
}
