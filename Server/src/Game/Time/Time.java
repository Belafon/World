package Game.Time;

import Game.World;
import Game.Time.DailyLoop.PartOfDay;

/* It has all methods to transfer number of ticks to our well known time, 
    It also has also has method getTime which returns number of ticks of the Game,
    when the Game is started, the current time from Clocks is saved, 
    getTime returns value current time from clocks - the time saved when the Game started */
public class Time {
    private int inception;
    private Clocks clocks;
    public volatile PartOfDay partOfDay;
    public Time(World game, Clocks clocks, DailyLoop dailyLoop){
        inception = clocks.time;
        this.clocks = clocks;
        partOfDay = dailyLoop.partsOfDay[0];
    }
    public int getTime(){
        return clocks.time - inception;
    }

    /* 20 ticks corresponds to 1 hour
     * 1 ticks corresponds to 3 minutes
     * 24x20, 480 ticks corresponds to 1 day
     * 30x24x20, 1440 ticks corrensponds to 1 month*/
    public int ticksOfToday(){
        return getTime() % 1440;
    }

    public int tickOfThisMonth(){
        return getTime() % 43200; 
    }

    public int ticksOfThisHour(){
        return getTime() % 60;
    }

    public String getDate(){
        return "Now is " + (getMonth(getTime()) + 1) + "th Month, " + (getDay(tickOfThisMonth()) + 1) + "th Day of this Month, " + (getHours(ticksOfToday()) + 1) + "th hour of this day and " + (getMinutes(ticksOfThisHour()) + 1) + "th minute of this hour." ;
    }

    public static int getMinutes(int numberOfTicks){
        return numberOfTicks;
    }

    public static float getHoursFloat(int numberOfTicks){
        return ((float) numberOfTicks) / 60f;
    }

    public static int getHours(int numberOfTicks){
        return numberOfTicks / 60;
    }

    public static float getDayFloat(int numberOfTicks){
        return ((float) numberOfTicks) / 1440f;
    }

    public static int getDay(int numberOfTicks){
        return numberOfTicks / 1440;
    }

    public static float getMonthFloat(int numberOfTicks){
        return ((float) numberOfTicks) / 43200f;
    }

    public static int getMonth(int numberOfTicks){
        return numberOfTicks / 43200;
    }

    public static int hoursToTicks(int hours){
        return hours * 60;
    }

    public static int daysToTicks(int days){
        return days * 1440;
    }

    public static int monthsToTicks(int months){
        return months * 43200;
    }
}


