package Server.Messages.PlayerMessages;

import Game.Creatures.Condition.Knowledge.Knowledge;
import Server.Messages.ConditionCreatureMessages;
import Server.SendMessage.SendMessagePlayer;

public class ConditionPlayerMessages extends ConditionCreatureMessages{
    public final SendMessagePlayer sendMessage;

    public ConditionPlayerMessages(SendMessagePlayer sendMessage) {
      this.sendMessage = sendMessage;
    }

    @Override
    public void setHealth(int health) {
		  sendMessage.sendLetter("player actualCondition health " + health);
    }
    @Override
    public void setHunger(int hunger) {
		  sendMessage.sendLetter("player actualCondition hunger " + hunger);
    }
    @Override
    public void setBleeding(int bleeding) {
		  sendMessage.sendLetter("player actualCondition bleeding " + bleeding);
    }
    @Override
    public void setHeat(int heat) {
		  sendMessage.sendLetter("player actualCondition heat " + heat);
    }
    @Override
    public void setFatigueMax(int fatigueMax) {
		  sendMessage.sendLetter("player actualCondition fatigueMax " + fatigueMax);
        }
        
    @Override
    public void addKnowledge(Knowledge knowledge) {
        sendMessage.sendLetter("player knowledge add " + knowledge.degree + " " + knowledge.type.name);
    }
}
