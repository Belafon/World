package com.belafon.Game.Calendar.Events;

import com.belafon.Game.Time.DailyLoop.PartOfDay;
import com.belafon.Game.Time.DailyLoop.namePartOfDay;
import com.belafon.Game.World;
import com.belafon.Game.Creatures.Creature;

public class EventPartOfDay extends Event{
    private PartOfDay partOfDay;
    public EventPartOfDay(long date, PartOfDay partOfDay, World game) {
        super(date, game);
        this.partOfDay = partOfDay;
    }

    @Override
    public void action(World game) {
        game.time.partOfDay = partOfDay;
        for(Creature creature : game.creatures) creature.writer.surrounding.setPartOfDay(partOfDay.name().name());
        if(partOfDay.name() == namePartOfDay.night)game.dailyLoop.addPlanToNextDay();
    }

    @Override
    public void interrupt(World game) {

    }
    
}
