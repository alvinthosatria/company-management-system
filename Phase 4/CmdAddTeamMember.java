public class CmdAddTeamMember extends RecordedCommand {
    private Team t;
    private Employee e;

    @Override
	public void execute(String[] cmdParts)
	{
		try {
            if (cmdParts.length < 3)
                throw new ExInsufficientArguments();

            Company c = Company.getInstance();
            t = c.findTeam(cmdParts[1]);
            e = c.findEmployee(cmdParts[2]);

			if (t.existMember(e))
				throw new ExEmployeeHasJoinedTeam();
            t.addMember(e);
			e.addTeamRole(t);
            
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list. Clear them.
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {  
			System.out.println(e.getMessage()); 
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeHasJoinedTeam e) {
			System.out.println(e.getMessage());
		}
		
	}

    @Override
	public void undoMe()
	{
		t.removeMember(e);
		e.removeTeamRole(t);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		t.addMember(e);
		e.addTeamRole(t);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
