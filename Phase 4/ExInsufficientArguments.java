public class ExInsufficientArguments extends Exception {
    public ExInsufficientArguments() { super("Insufficient command arguments!"); }

    public ExInsufficientArguments(Team t) {
        super("Missing input:  Please give the name of the acting head for "+t.getTeamName());
    }
    public ExInsufficientArguments(String message) { super(message); }
}