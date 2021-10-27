package Game.Time;

import Game.Game;
import Game.Calendar.Events.EventPartOfDay;
public class DailyLoop {
    private Game game;
    public DailyLoop(Game game){
        this.game = game;
        addToCalendar(0);
    }
    public PartOfDay[] partsOfDay = {
        new PartOfDay(60, namePartOfDay.after_midnight, -5),
        new PartOfDay(110, namePartOfDay.sunrise_1, -4),
        new PartOfDay(125, namePartOfDay.sunrise_2, -3),
        new PartOfDay(140, namePartOfDay.morning, -2),
        new PartOfDay(300, namePartOfDay.afternoon, 0),
        new PartOfDay(370, namePartOfDay.sunset_1, -2),
        new PartOfDay(385, namePartOfDay.sunset_2, -3),
        new PartOfDay(400, namePartOfDay.night, -4)
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
        int startOfnextDayInTicks = Time.getDay(game.time.getTime()) + 1; // number of next day
        startOfnextDayInTicks = Time.daysToTicks(startOfnextDayInTicks); // transfer days to ticks
        addToCalendar(startOfnextDayInTicks);
    }
    private void addToCalendar(int startOfnextDayInTicks) {
        for (PartOfDay partOfDay : partsOfDay)
            game.calendar.add(new EventPartOfDay(partOfDay.start + startOfnextDayInTicks, partOfDay, game));
    }

    public class PartOfDay{
        public final int start;
        public final namePartOfDay name;
        public final int temperatureChange; // 0 ->  temperature in the noon
        public PartOfDay(int start, namePartOfDay name, int temperatureChange){
            this.start = start;
            this.name = name;
            this.temperatureChange = temperatureChange;
        }
    }
}
