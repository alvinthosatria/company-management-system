import java.util.*;
import java.util.Collections;

public class Employee implements Comparable<Employee> {
    private String name;
    private int annualLeaves;
    private int sickLeaves;
    private ArrayList<Team> Roles;
    private ArrayList<LeaveRecord> allLeaves;

    public Employee(String n, int yle)
    {
        name = n;
        annualLeaves = yle;
        sickLeaves = 135;
        Roles = new ArrayList<>();
        allLeaves = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Team> getAllTeamsHeaded() {
        ArrayList<Team> allTeamsHeaded = new ArrayList<>();
        for (Team t: Roles) {
            if (t.getHead().name.equals(this.name))
                allTeamsHeaded.add(t);
        }

        return allTeamsHeaded;
    }

    public ArrayList<Team> getRoles() {
        return Roles;
    }

    public static void list(ArrayList<Employee> list)
    {
        for (Employee e: list)
            System.out.println(e.name+" (Entitled Annual Leaves: "+e.annualLeaves+" days)");
    }

    public void listRoles() {
        if (getRoleCount() == 0)
            System.out.println("No role");

        for (Team t: Roles) {
            if (name.equals(t.getHead().name))
                System.out.println(t.getTeamName() + " (Head of Team)");
            else {
                System.out.println(t.getTeamName());
            }
        }
    }

    public void listLeaves() {
        for (LeaveRecord l: allLeaves) {
            System.out.println(l.toString());
        }

        if (allLeaves.size() == 0)
            System.out.println("No leave record");
    }

    public int getRoleCount() {
        return Roles.size();
    }

    public boolean isHeadOfTeam() {
        for (Team t: Roles) {
            if (name.equals(t.getHead().name))
                return true;
        }

        return false;
    }

    public void addTeamRole(Team t) {
        Roles.add(t);
        Collections.sort(Roles);
    }

    public void removeTeamRole(Team t) {
        if (existRole(t))
            Roles.remove(t);
    }

    public void deductLeaveBalance(LeaveRecord l) {
        if (l.getType().equals("SL"))
            this.sickLeaves -= l.getDuration();

        else if (!l.getType().equals("NL"))
            this.annualLeaves -= l.getDuration();
    }

    public void addLeaveBalance(LeaveRecord l) {
        if (l.getType().equals("SL"))
            this.sickLeaves += l.getDuration();
            
        else if (!l.getType().equals("NL"))
            this.annualLeaves += l.getDuration();
    }

    public boolean existBlockLeave() {
        for (LeaveRecord lr: allLeaves) {
            if (lr.getType().equals("BL"))
                return true;
        }

        return false;
    }

    public void checkOverlappedLeaves(LeaveRecord l) throws ExOverlappedLeaves {
        for (LeaveRecord lr: allLeaves) {
            if (l.isOverlapping(lr))
                throw new ExOverlappedLeaves(lr);
        }
    }

    public void checkActingHeadLeave(LeaveRecord l) throws ExOverlappedLeaves {
        for (LeaveRecord lr: allLeaves) {
            if (l.isOverlapping(lr))
                throw new ExOverlappedLeaves(this, lr);
        }
    }

    public void checkBalance(LeaveRecord l) throws ExInsufficientAnnualLeaveBalance, ExInsufficientSickLeaveBalance, ExInvalidLeave {
        boolean isSickLeave = l.getType().equals("SL");
        boolean isNoPayLeave = l.getType().equals("NL");
        boolean isShortLeave = l.getType().equals("AL");
        boolean hasBlockLeave = this.existBlockLeave();

        if (isSickLeave && l.getDuration() > this.sickLeaves)
            throw new ExInsufficientSickLeaveBalance(this.sickLeaves);

        else if (!isSickLeave && !isNoPayLeave && l.getDuration() > this.annualLeaves)
            throw new ExInsufficientAnnualLeaveBalance(this.annualLeaves);
            
        else if (isShortLeave && (this.annualLeaves - l.getDuration()) < 7 && !hasBlockLeave)
            throw new ExInvalidLeave(this.annualLeaves, this.annualLeaves - 7);
    }

    public void checkActingHeadRole(LeaveRecord l) throws ExCannotTakeLeave {
        for (Team t: Roles) {
            for (ActingHead ah: t.getActingHeads()) {
                if (ah.getEmployee().getName().equals(name) && ah.getLeaveRecord().isOverlapping(l))
                    throw new ExCannotTakeLeave(this, t, ah.getLeaveRecord());
            }
        }
    }

    public void addLeave(LeaveRecord l) throws ExInsufficientSickLeaveBalance, ExInsufficientAnnualLeaveBalance, ExInvalidLeave, ExOverlappedLeaves, ExCannotTakeLeave {
        this.checkOverlappedLeaves(l);
        this.checkBalance(l);
        this.checkActingHeadRole(l);
        allLeaves.add(l);
        this.deductLeaveBalance(l);
        Collections.sort(allLeaves);
    }

    public void removeLeave(LeaveRecord l) {
        allLeaves.remove(l);
        this.addLeaveBalance(l);
    }

    public boolean existRole(Team t1) {
        for (Team t: Roles) {
            if (t.getTeamName().equals(t1.getTeamName()));
                return true;
        }

        return false;
    }

    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) {
        for (Employee e: list) {
            if (e.getName().equals(nameToSearch))
                return e;
        }

        return null;
    }
    
    @Override
    public int compareTo(Employee another)
    {
        if (this.name.equals(another.name)) return 0;
        else if (this.name.compareTo(another.name) > 0) return 1;
        else return -1;
    }
}
