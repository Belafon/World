package com.belafon.Game.Creatures;

import com.belafon.Server.SendMessage.MessageSender;

public class InfluencingActivities {
    private MessageSender writer;

    public InfluencingActivities(MessageSender writer) {
        this.writer = writer;
    }

    public void otherCreaturesBehaviourChanged(Creature creature) {
        writer.creatureVisible.behaviourChanged(creature);
    }

}
