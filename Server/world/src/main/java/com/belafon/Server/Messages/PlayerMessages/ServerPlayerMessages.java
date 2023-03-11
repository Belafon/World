package com.belafon.Server.Messages.PlayerMessages;

import com.belafon.Server.Messages.ServerMessages;
import com.belafon.Server.SendMessage.PlayersMessageSender;

public class ServerPlayerMessages extends ServerMessages{
  public final PlayersMessageSender sendMessage;

  public ServerPlayerMessages(PlayersMessageSender sendMessage) {
    this.sendMessage = sendMessage;
  }
  @Override
  public void setNumberOfPlayersInQueue(int number) {
		sendMessage.sendLetter("server number_of_players_to_wait " + number);
	}
  @Override
  public void startGame() {
		sendMessage.sendLetter("server startGame");
  }
}
