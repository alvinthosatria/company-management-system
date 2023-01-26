public abstract class LeaveRecord implements Comparable<LeaveRecord> {
    protected Day start;
    protected Day end;
    protected int duration;

    public LeaveRecord(Day s, Day e, int d)
    {
        this.start = s;
        this.end = e;
        this.duration = d;
    }

    public Day getStartDay() {
        return start;
    }

    public Day getEndDay() {
        return end;
    }

    protected boolean isOverlapping(LeaveRecord lr) {
        boolean overlap = false;
        int startDate = this.start.toInteger();
        int endDate = this.end.toInteger();
        int startToCompare = lr.start.toInteger();
        int endToCompare = lr.end.toInteger();

        if ((startDate >= startToCompare && startDate <= endToCompare) ||
        endDate >= startToCompare && endDate <= endToCompare) 
            overlap = true;
        else if (startDate < startToCompare && endDate > endToCompare )
            overlap = true;
        
        return overlap;
    }

    public abstract int getDuration();
    public abstract String toString();
    public abstract String getType();
}
