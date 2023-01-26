public class CmdsetupTeam extends RecordedCommand {

    private Team t;
	private Employee hd;

    @Override
	public void execute(String[] cmdParts)
	{
		try {
			if (cmdParts.length < 3)
				throw new ExInsufficientArguments();
			Company c = Company.getInstance();
			hd = c.findEmployee(cmdParts[2]);
			t = c.createTeam(cmdParts[1], hd);
			c.addTeam(t);
			t.addMember(hd);
			hd.addTeamRole(t);

			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExTeamDuplicate e) {
			System.out.println(e.getMessage());
		} catch(ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		}
		
	}

    @Override
	public void undoMe()
	{
		Company c = Company.getInstance();
        c.removeTeam(t);
		t.removeMember(hd);
		hd.removeTeamRole(t);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		Company c = Company.getInstance();
        c.addTeam(t);
		t.addMember(hd);
		hd.addTeamRole(t);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
