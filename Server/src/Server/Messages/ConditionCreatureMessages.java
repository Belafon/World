package Server.Messages;

import Game.Creatures.Condition.Knowledge.Knowledge;

public abstract class ConditionCreatureMessages {
    public abstract void setHealth(int health);
    public abstract void setHunger(int hunger);
    public abstract void setBleeding(int bleeding);
    public abstract void setHeat(int heat);
    public abstract void setFatigueMax(int fatigueMax);
    public abstract void addKnowledge(Knowledge knowledge);
}
