package Game.Calendar.Events;

import Game.Time.DailyLoop.PartOfDay;
import Game.Time.DailyLoop.namePartOfDay;
import Game.Game;
import Game.Creatures.Creature;

public class EventPartOfDay extends Event{
    private PartOfDay partOfDay;
    public EventPartOfDay(int date, PartOfDay partOfDay, Game game) {
        super(date, game);
        this.partOfDay = partOfDay;
    }

    @Override
    public void action(Game game) {
        game.time.partOfDay = partOfDay;
        for(Creature creature : game.creatures) creature.writer.surrounding.setPartOfDay(partOfDay.name.name());
        if(partOfDay.name == namePartOfDay.night)game.dailyLoop.addPlanToNextDay();
    }

    @Override
    public void interrupt(Game game) {

    }
    
}
