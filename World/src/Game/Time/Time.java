package Game.Time;

/* It has all methods to transfer number of ticks to our well known time, 
    It also has also has method getTime which returns number of ticks of the Game,
    when the Game is started, the current time from Clocks is saved, 
    getTime returns value current time from clocks - the time saved when the Game started */
public class Time {
    private int inception;
    private Clocks clocks;
    public Time(Clocks clocks){
        inception = clocks.time;
        this.clocks = clocks;
    }
    public int getTime(){
        return clocks.time - inception;
    }

    /* 20 ticks corresponds to 1 hour
     * 1 ticks corresponds to 3 minutes
     * 24x20, 480 ticks corresponds to 1 day
     * 30x24x20, 1440 ticks corrensponds to 1 month*/
    public int ticksOfToday(){
        return getTime() % 480;
    }

    public int tickOfThisMonth(){
        return getTime() % 14400; 
    }

    public int ticksOfThisHour(){
        return getTime() % 20;
    }

    public String getDate(){
        return "Now is " + (getMonth(getTime()) + 1) + "th Month, " + (getDay(tickOfThisMonth()) + 1) + "th Day of this Month, " + (getHours(ticksOfToday()) + 1) + "th hour of this day and " + (getMinutes(ticksOfThisHour()) + 1) + "th minute of this hour." ;
    }

    public static int getMinutes(int numberOfTicks){
        return numberOfTicks * 3;
    }

    public static float getHoursFloat(int numberOfTicks){
        return ((float) numberOfTicks) / 20f;
    }

    public static int getHours(int numberOfTicks){
        return numberOfTicks / 20;
    }

    public static float getDayFloat(int numberOfTicks){
        return ((float) numberOfTicks) / 480f;
    }

    public static int getDay(int numberOfTicks){
        return numberOfTicks / 480;
    }

    public static float getMonthFloat(int numberOfTicks){
        return ((float) numberOfTicks) / 14400f;
    }

    public static int getMonth(int numberOfTicks){
        return numberOfTicks / 14400;
    }

    public static int hoursToTicks(int hours){
        return hours * 20;
    }

    public static int daysToTicks(int days){
        return days * 480;
    }

    public static int monthsToTicks(int months){
        return months * 14400;
    }
}


