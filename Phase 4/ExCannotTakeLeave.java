public class ExCannotTakeLeave extends Exception {
    public ExCannotTakeLeave(Employee e, Team t, LeaveRecord l) {
        super("Cannot take leave. "+e.getName()+" is the acting head of " + t.getTeamName() + " during " + l.getStartDay().toString() + " to " + l.getEndDay().toString()+"!");
    }

    public ExCannotTakeLeave(String message) {
        super(message);
    }
}
