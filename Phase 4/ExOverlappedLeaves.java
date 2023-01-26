public class ExOverlappedLeaves extends Exception {
    public ExOverlappedLeaves(LeaveRecord l) {
        super("Leave overlapped: The leave period " + l.toString() + " is found!");
    }

    public ExOverlappedLeaves(Employee e, LeaveRecord l) {
        super(e.getName() + " is on leave during " + l.toString()+"!");
    }
}
