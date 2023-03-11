package com.belafon.Game.Maps;

import com.belafon.Game.Maps.MapGenerators.BasicGenerator;
import com.belafon.Game.World;

public class Maps {
    public Map[] maps;
    public Maps(World game){
        maps = new Map[] {
            new Map(10, 10, new BasicGenerator(), 0, 36, game)
        };
    } 
}
