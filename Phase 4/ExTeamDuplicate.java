public class ExTeamDuplicate extends Exception {
    public ExTeamDuplicate() {
        super("Team already exists!");
    }

    public ExTeamDuplicate(String message) {
        super(message);
    }
}
