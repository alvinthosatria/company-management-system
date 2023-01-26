import java.util.ArrayList;
import java.util.Collections; //this provides sorting

public class Team implements Comparable<Team> {
    private String teamName;
    private Employee head;
    private Day dateSetup;
    private ArrayList<Employee> allMembers;
    private ArrayList<ActingHead> allActingHeads;

    public Team(String n, Employee hd)
    {
        teamName = n;
        head = hd;
        dateSetup = SystemDate.getInstance().copyDay();
        allMembers = new ArrayList<>();
        allActingHeads = new ArrayList<>();
    }

    public static void list(ArrayList<Team> list)
    {
        System.out.printf("%-30s%-10s%-13s\n","Team Name", "Leader", "Setup Date");
        for (Team t: list)
            System.out.printf("%-30s%-10s%-13s\n", t.teamName, t.head.getName(), t.dateSetup);
    }

    public void listMembers() {
        for (Employee e: allMembers) {
            if (e.getName().equals(head.getName()))
                System.out.println(e.getName() + " (Head of Team)");
            else {
                System.out.println(e.getName());
            }
        }
    }

    public void listActingHeads() {
        for (ActingHead ahead: allActingHeads) {
            LeaveRecord l = ahead.getLeaveRecord();
            System.out.println(l.getStartDay().toString() + " to "+ l.getEndDay().toString() + ": " + ahead.getEmployee().getName());
        }
    }

    public boolean existMember(Employee e1) {
        for (Employee e: allMembers) {
            if (e.getName().equals(e1.getName()))
                return true;
        }

        return false;
    }

    public ArrayList<ActingHead> getActingHeads() {
        return allActingHeads;
    }

    public ActingHead findActingHead(Employee e) {
        for (ActingHead ah: allActingHeads) {
            if (ah.getEmployee().getName().equals(e.getName())) {
                return ah;
            }
        }

        return null;
    }

    public void addActingHead(ActingHead ah) {
        allActingHeads.add(ah);
        Collections.sort(allActingHeads);
    }

    public void removeActingHead(ActingHead ah) {
        allActingHeads.remove(ah);
    }

    public String getTeamName() {
        return teamName;
    }

    public Employee getHead() {
        return head;
    }

    public void addMember(Employee e) {
        allMembers.add(e);
        Collections.sort(allMembers);
    }

    public void removeMember(Employee e) {
        allMembers.remove(e);
    }


    @Override
    public int compareTo(Team another)
    {
        return this.teamName.compareTo(another.teamName);
    }
}
