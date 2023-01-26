public class CmdHire extends RecordedCommand {

    private Employee e;

    @Override
	public void execute(String[] cmdParts)
	{
		try {
			if (cmdParts.length < 3)
				throw new ExInsufficientArguments();
			Company company = Company.getInstance();
			e = company.createEmployee(cmdParts[1], Integer.parseInt(cmdParts[2]));
			company.addEmployee(e);
			
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeDuplicate e) {  
			System.out.println(e.getMessage()); 
		} catch (ExAnnualLeaveOutOfRange e) {
			System.out.println(e.getMessage());
		}
		
	}

    @Override
	public void undoMe()
	{
		Company company = Company.getInstance();
		company.removeEmployee(e);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		Company company = Company.getInstance();
		company.addEmployee(e);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
