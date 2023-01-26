public class SystemDate

{
    private static SystemDate instance;
    private Day d;

    public Day copyDay()
    {
        return d.clone();
    }

    public void set(String sDay) {
        d.set(sDay);
    }
    private SystemDate(String sDay) { this.d = new Day(sDay); }
    
    public static SystemDate getInstance() { return instance; }

    public static void createTheInstance(String sDay ) {
        instance = new SystemDate(sDay);
    }

    public static void changeInstance(SystemDate sd) {
        instance = sd;
    }
}