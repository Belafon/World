package com.belafon.Game.Time;

import com.belafon.Game.World;
import com.belafon.Game.Calendar.Events.EventPartOfDay;

/**
 * This class represents the daily loop in the game world. It is responsible for keeping track of the different
 * parts of the day and adding them to the game calendar as events. It uses a World object to access the game time and calendar.
 */
public class DailyLoop {
    private World game;

    /**
     * Constructor for DailyLoop class. Initializes the World object and adds the first day's events to the calendar.
     *
     * @param game The World object for the game.
     */
    public DailyLoop(World game) {
        this.game = game;
        addToCalendar(0);
    }

    /**
     * A list containing the different parts of the day with their start time, name, and temperature change.
     */
    public PartOfDay[] partsOfDay = {
            new PartOfDay(180, NamePartOfDay.after_midnight, -5),
            new PartOfDay(330, NamePartOfDay.sunrise_1, -4),
            new PartOfDay(375, NamePartOfDay.sunrise_2, -3),
            new PartOfDay(420, NamePartOfDay.morning, -2),
            new PartOfDay(900, NamePartOfDay.afternoon, 0),
            new PartOfDay(1110, NamePartOfDay.sunset_1, -2),
            new PartOfDay(1155, NamePartOfDay.sunset_2, -3),
            new PartOfDay(1200, NamePartOfDay.night, -4)
    };

    public enum NamePartOfDay {
        after_midnight, // starts at 3.0 hours today -> 60 of ticks today
        sunrise_1, // 5.5 -> 110
        sunrise_2, // 6.25 -> 125
        morning, // 7.0 -> 140
        afternoon, // 15.0 -> 300
        sunset_1, // 18.5 -> 370
        sunset_2, // 19.25 -> 385
        night // 20.0 -> 400
    }

    /**
     * Adds the events for the next day's parts of the day to the game calendar.
     */
    public void addPlanToNextDay() {
        long startOfnextDayInTicks = Time.getDay(game.time.getTime()) + 1; // number of next day
        startOfnextDayInTicks = Time.daysToTicks(startOfnextDayInTicks); // transfer days to ticks
        addToCalendar(startOfnextDayInTicks);
    }

    /**
     * Adds the events for the parts of the day to the game calendar.
     *
     * @param startOfnextDayInTicks The start time of the next day in ticks.
     */
    private void addToCalendar(long startOfnextDayInTicks) {
        for (PartOfDay partOfDay : partsOfDay)
            game.calendar.add(new EventPartOfDay(partOfDay.start + startOfnextDayInTicks, partOfDay, game));
    }

    public record PartOfDay(int start, NamePartOfDay name, int temperatureChange) {
    }
}
