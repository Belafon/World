package Game.Time;

import Game.Game;
import Game.Calendar.Events.EventPartOfDay;
public class DailyLoop {
    private Game game;
    public DailyLoop(Game game){
        this.game = game;
        addToCalendar(0);
    }
    public enum PartOfDay{
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
        game.calendar.add(new EventPartOfDay(startOfnextDayInTicks + 60, PartOfDay.after_midnight, game));
        game.calendar.add(new EventPartOfDay(startOfnextDayInTicks + 110, PartOfDay.sunrise_1, game));
        game.calendar.add(new EventPartOfDay(startOfnextDayInTicks + 125, PartOfDay.sunrise_2, game));
        game.calendar.add(new EventPartOfDay(startOfnextDayInTicks + 140, PartOfDay.morning, game));
        game.calendar.add(new EventPartOfDay(startOfnextDayInTicks + 300, PartOfDay.afternoon, game));
        game.calendar.add(new EventPartOfDay(startOfnextDayInTicks + 370, PartOfDay.sunset_1, game));
        game.calendar.add(new EventPartOfDay(startOfnextDayInTicks + 385, PartOfDay.sunset_2, game));
        game.calendar.add(new EventPartOfDay(startOfnextDayInTicks + 400, PartOfDay.night, game));
    }
}
