public class ActingHead implements Comparable<ActingHead>{
    private Employee e;
    private LeaveRecord l;

    public ActingHead(Employee emp, LeaveRecord lr) {
        e = emp;
        l = lr;
    }

    public Employee getEmployee() {
        return e;
    }

    public LeaveRecord getLeaveRecord() {
        return l;
    }

    @Override
    public int compareTo(ActingHead another)
    {
        if (l.getStartDay().toInteger() == another.l.getStartDay().toInteger()) return 0;
        else if (l.getStartDay().toInteger() > another.l.getStartDay().toInteger()) return 1;
        else return -1;
    }
}
