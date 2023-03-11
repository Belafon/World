package com.belafon.Game.Time;

import com.belafon.Game.World;
import com.belafon.Game.Time.DailyLoop.PartOfDay;

/* It has all methods to transfer number of ticks to our well known time, 
    It also has also has method getTime which returns number of ticks of the Game,
    when the Game is started, the current time from Clocks is saved, 
    getTime returns value current time from clocks - the time saved when the Game started */
public class Time {
    private long inception;
    private Clocks clocks;
    public volatile PartOfDay partOfDay;
    public Time(World game, Clocks clocks, DailyLoop dailyLoop){
        inception = clocks.time;
        this.clocks = clocks;
        partOfDay = dailyLoop.partsOfDay[0];
    }
    public long getTime(){
        return clocks.time - inception;
    }

    /* 20 ticks corresponds to 1 hour
     * 1 ticks corresponds to 3 minutes
     * 24x20, 480 ticks corresponds to 1 day
     * 30x24x20, 1440 ticks corrensponds to 1 month*/
    public long ticksOfToday(){
        return getTime() % 1440;
    }

    public long tickOfThisMonth(){
        return getTime() % 43200; 
    }

    public long ticksOfThisHour(){
        return getTime() % 60;
    }

    public String getDate(){
        return "Now is " + (getMonth(getTime()) + 1) + "th Month, " + (getDay(tickOfThisMonth()) + 1)
                + "th Day of this Month, " + (getHours(ticksOfToday()) + 1) + "th hour of this day and "
                + (getMinutes(ticksOfThisHour()) + 1) + "th minute of this hour.";
    }

    public static long getMinutes(long numberOfTicks){
        return numberOfTicks;
    }

    public static float getHoursFloat(long numberOfTicks){
        return ((float) numberOfTicks) / 60f;
    }

    public static long getHours(long numberOfTicks){
        return numberOfTicks / 60;
    }

    public static float getDayFloat(long numberOfTicks){
        return ((float) numberOfTicks) / 1440f;
    }

    public static long getDay(long numberOfTicks){
        return numberOfTicks / 1440;
    }

    public static float getMonthFloat(long numberOfTicks){
        return ((float) numberOfTicks) / 43200f;
    }

    public static long getMonth(long numberOfTicks){
        return numberOfTicks / 43200;
    }

    public static long hoursToTicks(long hours){
        return hours * 60;
    }

    public static long daysToTicks(long days){
        return days * 1440;
    }

    public static long monthsToTicks(long months){
        return months * 43200;
    }
}


