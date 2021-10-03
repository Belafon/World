package Game.Calendar.Events;

import Console.ConsolePrint;
import Game.Game;
import Game.Maps.Weather.Sky;

public class EventChangeWeather extends Event{
    private Sky sky;
    public EventChangeWeather(int date, Game game, Sky sky, boolean printInLoop) {
        super(date, game);
        this.sky = sky;
        this.printInLoop = printInLoop;
    }

    private final boolean printInLoop; 

    @Override
    public void action(Game game) {
        new Thread(new Runnable(){
            @Override
            public void run() {
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
    public void interrupt(Game game) {
        
    }
    
}
/*

git clone https://github.com/Belafon/World.git
Username: Belafon
Password: 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


*/