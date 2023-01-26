public class LeaveRecord_NL extends LeaveRecord {
    private String type;

    public LeaveRecord_NL(Day s, Day e, int d)
    {
        super(s, e, d);
        type = "NL";
    }

    public String getType() {
        return type;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return start.toString() + " to " + end.toString() + " [NL]";
    }

    @Override
    public int compareTo(LeaveRecord another)
    {
        if (this.start.toInteger() == another.start.toInteger()) return 0;
        else if (this.start.toInteger() > another.start.toInteger()) return 1;
        else return -1;
    }

    
}
