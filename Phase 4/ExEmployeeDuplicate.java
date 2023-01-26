public class ExEmployeeDuplicate extends Exception {
    public ExEmployeeDuplicate()
    {
        super("Employee already exists!");
    }

    public ExEmployeeDuplicate(String message){
        super(message);
    }
}
