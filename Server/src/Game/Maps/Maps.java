package Game.Maps;

import Game.Maps.MapGenerators.BasicGenerator;
import Game.Game;

public class Maps {
    public Map[] maps;
    public Maps(Game game){
        maps = new Map[] {
            new Map(10, 10, new BasicGenerator(), 0, 12, game)
        };
    } 
}
