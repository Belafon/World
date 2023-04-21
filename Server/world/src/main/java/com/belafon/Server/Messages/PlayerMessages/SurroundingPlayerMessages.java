package com.belafon.Server.Messages.PlayerMessages;

import com.belafon.Game.Maps.Map;
import com.belafon.Game.Maps.Place.ItemPlace;
import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.Maps.Resources.Resource;
import com.belafon.Game.Maps.Weather.Weather;
import com.belafon.Game.ObjectsMemory.Visible;
import com.belafon.Game.Time.DailyLoop.NamePartOfDay;
import com.belafon.Server.Messages.SurroundingMessages;
import com.belafon.Server.SendMessage.PlayersMessageSender;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Creatures.Behaviour.PlayersLookAround;
import com.belafon.Game.Items.Item;

public class SurroundingPlayerMessages implements SurroundingMessages {
    public final PlayersMessageSender sendMessage;

    public SurroundingPlayerMessages(PlayersMessageSender sendMessage) {
        this.sendMessage = sendMessage;
    }

    @Override
    public void setPartOfDay(NamePartOfDay partOfDay) {
        sendMessage.sendLetter("map partOfDay " + partOfDay.name());
    }

    @Override
    public void setWeather(Weather weather) {
        sendMessage.sendLetter("map weather " + weather.getWeather());
    }

    @Override
    public void setClouds(Place place) {
        sendMessage.sendLetter("map clouds " + place.map.sky.getWeather(place).getClouds());
    }

    @Override
    public void setInfoAboutSurroundingPlacesLookAround(Place position) {
        String look = PlayersLookAround.look(sendMessage.client.actualGame, sendMessage.client.player, position);
        String message = "map look_arround " + look;
        sendMessage.client.writer.sendLetter(message);
    }

    @Override
    public void setPosition(Place position) {
        setInfoAboutPlace(position);
        setInfoAboutSurrounding(position);
    }

    @Override
    public void setInfoAboutPlace(Place place) {
        // setInfoAboutPlace needs to be filled
    }

    @Override
    public void setInfoAboutSurrounding(UnboundedPlace position) {
        setTypeOfPlaceInfoDrawableSound(position);
        if (position instanceof Place place) {
            setWeather(place.map.sky.getWeather(place.positionX, place.positionY));
            setClouds(place);
            setInfoAboutSurroundingPlacesLookAround(place);
        }
        setResources(position); // items which u can find
        setItems(position);
    }

    @Override
    public void setResources(UnboundedPlace position) {
        StringBuilder message = new StringBuilder("surrounding resources");
        for (Resource resource : position.resourcesSorted)
            if (resource.durationOfFinding == 0)
                message.append(" " + resource.type.name);
            else
                break;
        sendMessage.client.writer.sendLetter(message.toString());
    }

    @Override
    public void setResource(Resource resource) {
        sendMessage.client.writer.sendLetter("surrounding resource " + resource.type.name);
    }

    @Override
    public void setResourceNotFound(Resource resource) {
        sendMessage.client.writer.sendLetter("surrounding resourceNotFound " + resource.type.name);
    }

    @Override
    public void setItems(UnboundedPlace position) {
        // TODO fill setItems method
    }

    @Override
    public void setTypeOfPlaceInfoDrawableSound(UnboundedPlace position) {
        sendMessage.sendLetter("soundDrawable " + position.music + " " + position.picture);
    }

    @Override
    public void setNewMap(Map map, int sizeX, int sizeY) {
        sendMessage.sendLetter("map new_map " + map.id + " " + sizeX + " " + sizeY);
    }

    @Override
    public void addVisibleInSight(Visible visible) {
        StringBuilder message = new StringBuilder("surrounding");
        if (visible instanceof Item item) {
            message.append(" add_item_in_sight "
                    + getVisibleItemPropertiesMessage(item));

        } else if (visible instanceof Creature creature) {
            message.append(" add_creature_in_sight "
                    + getVisibleCreaturePropertiesMessage(creature));

        } else if (visible instanceof Resource resource) {
            message.append(" add_resource_in_sight "
                    + getVisibleResourcePropertiesMessage(resource));
        }
        
        sendMessage.client.writer.sendLetter(message.toString());
    }

    private StringBuilder getVisibleItemPropertiesMessage(Item item) {
        return new StringBuilder(item.id + " " + item.type.getClass().getSimpleName() + " "
                + item.type.name + " " + item.type.regularWeight + " "
                + item.type.visibility + " " + item.type.toss + " "
                + InventoryPlayerMessages.getItemPropertiesMessage(item));
    }

    private StringBuilder getVisibleResourcePropertiesMessage(Resource resource) {
        return new StringBuilder(resource.id + " " + resource.type.name);
    }

    private StringBuilder getVisibleCreaturePropertiesMessage(Creature creature) {
        StringBuilder creaturesBehaviour;
        if (creature.currentBehaviour == null)
            creaturesBehaviour = new StringBuilder("idle");
        else
            creaturesBehaviour = new StringBuilder(creature.currentBehaviour.getType().behaviourClass.getSimpleName());

        return new StringBuilder(creature.id + " " + creature.name + " "
                + creature.appearence.replace(" ", "_")
                + " " + getMessageAboutPlace(creature.getLocation()) + " " + creaturesBehaviour);
    }

    private StringBuilder getMessageAboutPlace(UnboundedPlace place) {
        StringBuilder message = new StringBuilder();
        if (place instanceof Place p) {
            message.append("place " + p.map.id + " " + p.positionX + " " + p.positionX);
        } else if (place instanceof ItemPlace p) {
            message.append("itemPlace " + p.getItem().id);
        }
        return message;
    }

    @Override
    public void removeVisibleFromSight(Visible value) {
        sendMessage.sendLetter("surrounding");
        StringBuilder message = new StringBuilder("surrounding");
        if (value instanceof Item item) {
            message.append(" remove_item_in_sight "
                    + item.id);
        } else if (value instanceof Creature creature) {
            message.append(" remove_creature_in_sight "
                    + creature.id);

        } else if (value instanceof Resource resource) {
            message.append(" remove_resource_in_sight "
                    + resource.type.name);
        }
        sendMessage.client.writer.sendLetter(message.toString());
    }
}
