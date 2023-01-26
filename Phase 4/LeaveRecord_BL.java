public class LeaveRecord_BL extends LeaveRecord {
    String type;

    public LeaveRecord_BL(Day s, Day e, int d)
    {
        super(s, e, d);
        type = "BL";
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
        return start.toString() + " to " + end.toString() + " [BL]";
    }

    @Override
    public int compareTo(LeaveRecord another)
    {
        if (this.start.toInteger() == another.start.toInteger()) return 0;
        else if (this.start.toInteger() > another.start.toInteger()) return 1;
        else return -1;
    }
}
