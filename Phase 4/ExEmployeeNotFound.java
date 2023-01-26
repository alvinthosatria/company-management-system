public class ExEmployeeNotFound extends Exception {
    public ExEmployeeNotFound()
    {
        super("Employee not found!");
    }

    public ExEmployeeNotFound(String EmployeeName, Team t) {
        super("Employee ("+EmployeeName+") not found for "+t.getTeamName()+"!");
    }

    public ExEmployeeNotFound(String message){
        super(message);
    }
}

