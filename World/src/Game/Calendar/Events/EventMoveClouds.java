package Game.Calendar.Events;

import Console.ConsolePrint;
import Game.Game;
import Game.Maps.Map;

public class EventMoveClouds extends Event{
    private final Map map;
    public EventMoveClouds(int date, Game game, Map map, boolean printInLoop) {
        super(date, game);
        this.map = map;
        this.printInLoop = printInLoop;
    }

    private final boolean printInLoop; 

    @Override
    public void action(Game game) {
        if(printInLoop){
            ConsolePrint.gameInfo("before CLOUD change:");
            ConsolePrint.gameInfo(map.sky.printClouds());
        }
        map.sky.moveClouds();
        if(printInLoop){
            ConsolePrint.gameInfo("after CLOUD change:");
            ConsolePrint.gameInfo(map.sky.printClouds());
        }
    }

    @Override
    public void interrupt(Game game) {
        
    }
    
}
