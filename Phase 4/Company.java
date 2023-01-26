import java.util.ArrayList;
import java.util.Collections; //this provides sorting

public class Company {
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;

    private static Company instance = new Company(); //The instance created when the class is loaded

    private Company() {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
    }

    public static Company getInstance() { return instance; }

    public void listTeams()
    {
        Team.list(allTeams);
    }

    public void listEmployees() 
    {
        Employee.list(allEmployees);
    }

    public void listTeamMembers() 
    {
        for (Team t: allTeams) {
            System.out.println(t.getTeamName() + ":");
            t.listMembers();
            if (t.getActingHeads().size() > 0) {
                System.out.println("Acting heads:");
                t.listActingHeads();
                System.out.println();
            } else {
                System.out.println();
            }
           
        }
    }

    public void listLeaves() {
        for (Employee e: allEmployees) {
            System.out.println(e.getName()+":");
            e.listLeaves();
        }
    }

    public void assignActingHeads(String[] actingHeads, LeaveRecord l) throws ExTeamNotFound, ExEmployeeNotFound, ExOverlappedLeaves {
        Team t;
        Employee e;

        ArrayList<Team> teams = new ArrayList<>();
        ArrayList<ActingHead> actingheads = new ArrayList<>();

        for (int i = 0; i<actingHeads.length; i+=2) {
            t = findTeam(actingHeads[i]);
            e = Employee.searchEmployee(allEmployees, actingHeads[i+1]);

            if (e == null || !t.existMember(e))
                throw new ExEmployeeNotFound(actingHeads[i+1], t);

            e.checkActingHeadLeave(l);
            
            //store teams and acting heads to be assign
            teams.add(t);
            actingheads.add(new ActingHead(e, l));
        }

        //assign all the legitimate acting heads when there is no exception
        for (int i=0; i<teams.size(); i++) {
            Team team = teams.get(i);
            ActingHead ah = actingheads.get(i);
            // if (!team.existActingHead(ah))
            team.addActingHead(ah);
        }
    }

    public void dischargeActingHeads(String[] actingHeads, LeaveRecord l) throws ExTeamNotFound, ExEmployeeNotFound {
        Team t;
        Employee e;
        for (int i =0; i<actingHeads.length; i+=2) {
            t = findTeam(actingHeads[i]);
            e = findEmployee(actingHeads[i+1]);

            ActingHead actHead = t.findActingHead(e);

            t.removeActingHead(actHead);
        }
    }

    public Employee createEmployee(String name, int n) throws ExEmployeeDuplicate, ExAnnualLeaveOutOfRange
    {
        if (n > 300)
            throw new ExAnnualLeaveOutOfRange();
        if (Employee.searchEmployee(allEmployees, name) != null)
            throw new ExEmployeeDuplicate();

        Employee e1 = new Employee(name, n);
        return e1;
    }

    public Team createTeam(String tn, Employee hd) throws ExTeamDuplicate
    {
        Team t = new Team(tn, hd); //e is the head
        if (existTeam(tn)) 
            	throw new ExTeamDuplicate();
        return t;
    }

    public Employee findEmployee(String name) throws ExEmployeeNotFound 
    {
        for (Employee e: allEmployees) {
            if (e.getName().equals(name))
                return e;
        }

        throw new ExEmployeeNotFound();
    }

    public Team findTeam(String tn) throws ExTeamNotFound
    {
        for (Team t: allTeams) {
            if (t.getTeamName().equals(tn))
                return t;
        }

        throw new ExTeamNotFound();
    }

    public boolean existTeam(String tn) 
    {
        for (Team t: allTeams) {
            if (t.getTeamName().equals(tn))
                return true;
        }

        return false;
    }

    //undo and redo of set up team
    public void removeTeam(Team t) {
        allTeams.remove(t);
        for (Employee e: allEmployees) {
            e.removeTeamRole(t);
        }
    }

    public void addTeam(Team t) {
        allTeams.add(t);
        Collections.sort(allTeams);
    }

    //undo and redo of hire employee
    public void removeEmployee(Employee e) {
        allEmployees.remove(e);
    }

    public void addEmployee(Employee e) {
        allEmployees.add(e);
        Collections.sort(allEmployees);
    }
}
