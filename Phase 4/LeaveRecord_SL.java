public class LeaveRecord_SL extends LeaveRecord {
    private String type;

    public LeaveRecord_SL(Day s, Day e, int d)
    {
        super(s, e, d);
        type = "SL";
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
        return start.toString() + " to " + end.toString() + " [SL]";
    }

    @Override
    public int compareTo(LeaveRecord another)
    {
        if (this.start.toInteger() == another.start.toInteger()) return 0;
        else if (this.start.toInteger() > another.start.toInteger()) return 1;
        else return -1;
    }
}
