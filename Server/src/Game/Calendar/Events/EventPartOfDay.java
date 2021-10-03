package Game.Calendar.Events;

import Game.Time.DailyLoop;
import Game.Time.DailyLoop.PartOfDay;
import Game.Game;
import Game.Creatures.Player;

public class EventPartOfDay extends Event{
    private DailyLoop.PartOfDay partOfDay;
    public EventPartOfDay(int date, DailyLoop.PartOfDay partOfDay, Game game) {
        super(date, game);
        this.partOfDay = partOfDay;
    }

    @Override
    public void action(Game game) {
        for(Player player : game.players) player.client.writer.setPartOfDay(partOfDay.name());
        if(partOfDay == PartOfDay.night)game.dailyLoop.addPlanToNextDay();
    }

    @Override
    public void interrupt(Game game) {

    }
    
}
