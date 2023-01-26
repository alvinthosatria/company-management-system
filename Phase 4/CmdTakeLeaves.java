import java.util.Arrays;
import java.util.ArrayList;

public class CmdTakeLeaves extends RecordedCommand {
	private Employee e;
	private LeaveRecord l;
	String[] actingHeads;

    @Override
	public void execute(String[] cmdParts)
	{
		try {
			if (cmdParts.length < 3)
				throw new ExInsufficientArguments();
				
			Company company = Company.getInstance();
			e = company.findEmployee(cmdParts[1]);
			Day start = new Day(cmdParts[3]);
			if (SystemDate.getInstance().copyDay().toInteger() > start.toInteger())
				throw new ExDateHasAlreadyPassed(SystemDate.getInstance().copyDay());

			Day end = new Day(cmdParts[4]);
			int duration = Day.calculateDuration(start, end);

			//Create LeaveRecord Object
			if (cmdParts[2].equals("AL")) {
				if (duration >= 7)
					throw new ExAnnualLeaveOutOfRange("To apply annual leave for 7 days or more, please use the Block Leave (BL) type.");
				l = new LeaveRecord_AL(start, end, duration);

			} else if (cmdParts[2].equals("BL")) {
				if (duration < 7) 
					throw new ExAnnualLeaveOutOfRange("To apply annual leave for 6 days or less, you should use the normal Annual Leave (AL) type.");
				l = new LeaveRecord_BL(start, end, duration);

			} else if (cmdParts[2].equals("SL")) {
				l = new LeaveRecord_SL(start, end, duration);
				
			} else if (cmdParts[2].equals("NL")) {
				l = new LeaveRecord_NL(start, end, duration);
			}


			//Add leave and assignment of acting heads
			if (cmdParts.length > 5) {
				ArrayList<Team> allTeamsHeaded = e.getAllTeamsHeaded();
				actingHeads = Arrays.copyOfRange(cmdParts, 5, cmdParts.length);

				for (Team t: allTeamsHeaded) {
					if (!Arrays.asList(actingHeads).contains(t.getTeamName()))
						throw new ExInsufficientArguments(t);
				}

				//Assign acting heads if inputs are complete
				company.assignActingHeads(actingHeads, l);
			}

			e.addLeave(l);

			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e) {  
			System.out.println(e.getMessage());
		} catch (ExDateHasAlreadyPassed e) {
			System.out.println(e.getMessage());
		} catch (ExAnnualLeaveOutOfRange e) {
			System.out.println(e.getMessage());
		} catch (ExOverlappedLeaves e) {
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientSickLeaveBalance e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientAnnualLeaveBalance e) {
			System.out.println(e.getMessage());
		} catch (ExInvalidLeave e) {
			System.out.println(e.getMessage());
		} catch (ExCannotTakeLeave e) {
			System.out.println(e.getMessage());
		}
		
	}

    @Override
	public void undoMe()
	{
		e.removeLeave(l);
		try {
			Company company = Company.getInstance();
			company.dischargeActingHeads(actingHeads, l);
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {
			System.out.println(e.getMessage());
		}
		
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		try {
			e.addLeave(l);
			Company company = Company.getInstance();
			company.assignActingHeads(actingHeads, l);
		} catch (ExInsufficientSickLeaveBalance e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientAnnualLeaveBalance e) {
			System.out.println(e.getMessage());
		} catch (ExInvalidLeave e) {
			System.out.println(e.getMessage());
		} catch (ExOverlappedLeaves e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {
			System.out.println(e.getMessage());
		}  catch (ExCannotTakeLeave e) {
			System.out.println(e.getMessage());
		}
		
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
