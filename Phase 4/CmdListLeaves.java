public class CmdListLeaves implements Command {
    @Override
    public void execute(String[] cmdParts)
	{
        try {
            Company company = Company.getInstance();
            Employee e;
            if (cmdParts.length > 1) {
                e = company.findEmployee(cmdParts[1]);
                e.listLeaves();
            } else {
                company.listLeaves();
            }
        } catch (ExEmployeeNotFound e) {
            System.out.println(e.getMessage());
        }
		
        
	}
}
