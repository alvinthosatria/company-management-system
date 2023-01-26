public class ExEmployeeHasJoinedTeam extends Exception {
    public ExEmployeeHasJoinedTeam() {
        super("The employee has joined the team already!");
    }

    public ExEmployeeHasJoinedTeam(String message) {
        super(message);
    }
}
