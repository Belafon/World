package com.belafon.world.creatures;

import com.belafon.server.sendMessage.MessageSender;

public class InfluencingActivities {
    private MessageSender writer;

    public InfluencingActivities(MessageSender writer) {
        this.writer = writer;
    }

    public void otherCreaturesBehaviourChanged(Creature creature) {
        writer.creatureVisible.behaviourChanged(creature);
    }

}
