package com.belafon.Game.Time;

import com.belafon.Game.World;
import com.belafon.Game.Calendar.Events.EventPartOfDay;
public class DailyLoop {
    private World game;
    public DailyLoop(World game){
        this.game = game;
        addToCalendar(0);
    }
    public PartOfDay[] partsOfDay = {
        new PartOfDay(180, namePartOfDay.after_midnight, -5),
        new PartOfDay(330, namePartOfDay.sunrise_1, -4),
        new PartOfDay(375, namePartOfDay.sunrise_2, -3),
        new PartOfDay(420, namePartOfDay.morning, -2),
        new PartOfDay(900, namePartOfDay.afternoon, 0),
        new PartOfDay(1110, namePartOfDay.sunset_1, -2),
        new PartOfDay(1155, namePartOfDay.sunset_2, -3),
        new PartOfDay(1200, namePartOfDay.night, -4)
    };
    public enum namePartOfDay{
        after_midnight, // starts at 3.0 hours today -> 60 of ticks today
        sunrise_1,      // 5.5 -> 110
        sunrise_2,      // 6.25 -> 125
        morning,        // 7.0 -> 140
        afternoon,      // 15.0 -> 300
        sunset_1,       // 18.5 -> 370
        sunset_2,       // 19.25 -> 385
        night           // 20.0 -> 400
    }
    public void addPlanToNextDay(){
        long startOfnextDayInTicks = Time.getDay(game.time.getTime()) + 1; // number of next day
        startOfnextDayInTicks = Time.daysToTicks(startOfnextDayInTicks); // transfer days to ticks
        addToCalendar(startOfnextDayInTicks);
    }
    private void addToCalendar(long startOfnextDayInTicks) {
        for (PartOfDay partOfDay : partsOfDay)
            game.calendar.add(new EventPartOfDay(partOfDay.start + startOfnextDayInTicks, partOfDay, game));
    }

    public record PartOfDay(int start, namePartOfDay name, int temperatureChange){}
}
