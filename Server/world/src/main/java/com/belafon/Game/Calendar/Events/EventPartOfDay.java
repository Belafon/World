package com.belafon.Game.Calendar.Events;

import com.belafon.Game.Time.DailyLoop.PartOfDay;
import com.belafon.Game.Time.DailyLoop.NamePartOfDay;
import com.belafon.Console.ConsolePrint;
import com.belafon.Game.World;
import com.belafon.Game.Creatures.Creature;

public class EventPartOfDay extends Event {
    private PartOfDay partOfDay;

    public EventPartOfDay(long date, PartOfDay partOfDay, World game) {
        super(date, game);
        this.partOfDay = partOfDay;
    }

    @Override
    public void action(World game) {
        ConsolePrint.success("EventPartOfDay", "part of day changed " + partOfDay.name());
        game.time.partOfDay = partOfDay;
        for (Creature creature : game.creatures)
            creature.writer.surrounding.setPartOfDay(partOfDay.name());
        if (partOfDay.name() == NamePartOfDay.night)
            game.dailyLoop.addPlanToNextDay();
    }

    @Override
    public void interrupt(World game) {

    }

}
