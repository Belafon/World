package com.belafon.world.maps;

import com.belafon.world.World;
import com.belafon.world.maps.mapGenerators.BasicGenerator;

public class Maps {
    public Map[] maps;
    public Maps(World game){
        maps = new Map[] {
            new Map(10, 10, new BasicGenerator(), 0, 36, game)
        };
    } 
}
