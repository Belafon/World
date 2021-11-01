package Game.Calendar.Events;

import Console.ConsolePrint;
import Game.World;
import Game.Maps.Weather.Sky;

public class EventChangeWeather extends Event{
    private Sky sky;
    public EventChangeWeather(int date, World game, Sky sky, boolean printInLoop) {
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
/*

git clone https://github.com/Belafon/World.git
Username: Belafon
Password: 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


*/