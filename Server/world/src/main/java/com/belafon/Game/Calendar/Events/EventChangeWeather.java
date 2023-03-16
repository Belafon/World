package com.belafon.Game.Calendar.Events;

import com.belafon.Console.ConsolePrint;
import com.belafon.Game.World;
import com.belafon.Game.Maps.Weather.Sky;

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
                if(printInLoop){
                    ConsolePrint.gameInfo("after weather change:");
                    ConsolePrint.gameInfo(sky.printWeathers());    
                }
            }
        }).start();
    }

    @Override
    public void interrupt(World game) {
        
    }
    
}