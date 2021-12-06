package Game.Items.Types;

import Game.Game;
import Game.Items.Item;
import Game.Items.TypeItem.TypeItem;

public class Tool extends Item{
    public volatile int quality;

    public Tool(Game game, TypeItem type, int quality) {
        super(game, type);
        this.quality = quality;
    }
}
