package com.belafon.Game.Calendar.Events;

import com.belafon.Console.ConsolePrint;
import com.belafon.Game.World;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Weather.Sky;
import com.belafon.Game.Maps.Weather.Weather;

public class EventChangeWeather extends Event{
    private Sky sky;
    public EventChangeWeather(long date, World game, Sky sky, boolean printInLoop) {
        super(date, game);
        this.sky = sky;
        this.printInLoop = printInLoop;
    }

    private final boolean printInLoop; 

    private volatile int threadCounter = 0;
    @Override
    public void action(World game) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                Thread.currentThread().setName("SetWeather " + threadCounter++);
                if(printInLoop){
                    ConsolePrint.gameInfo("before weather change:");
                    ConsolePrint.gameInfo(sky.printWeathers());    
                }
                sky.updateWeather();
                if (printInLoop) {
                    ConsolePrint.gameInfo("after weather change:");
                    ConsolePrint.gameInfo(sky.printWeathers());
                }
                ConsolePrint.success("EventChangeWeather", "at map " + sky.map.id + " weather changed ");
                for (Creature creature : game.creatures) {
                    if (creature.getLocation() instanceof Place place) {
                        Weather weather = place.map.sky.getWeather(place);
                        if (place.map == sky.map) {
                            if (creature.memory.getLastWeather() != weather.getWeather()) {
                                creature.writer.surrounding.setWeather(weather);
                                creature.memory.setLastWeather(weather.getWeather());
                            }
                            
                            if(creature.memory.getLastSizeOfClouds() != weather.getClouds()){
                                creature.writer.surrounding.setClouds(place);
                                creature.memory.setLastSizeOfClouds(weather.getClouds());
                            }
                        }
                    }
                }
            }
        }).start();
    }

    @Override
    public void interrupt(World game) {
        
    }
    
}