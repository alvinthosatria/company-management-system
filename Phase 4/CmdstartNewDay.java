public class CmdstartNewDay extends RecordedCommand {

    SystemDate oldDate;
    SystemDate newDate;
    
    @Override
	public void execute(String[] cmdParts)
	{
		oldDate = SystemDate.getInstance();
        SystemDate.createTheInstance(cmdParts[1]);
        newDate = SystemDate.getInstance();
		addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
		System.out.println("Done.");
	}

    @Override
	public void undoMe()
	{
		SystemDate.changeInstance(oldDate);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		SystemDate.changeInstance(newDate);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
