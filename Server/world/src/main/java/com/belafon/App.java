package com.belafon;

import com.belafon.Game.Items.ListOfAllItemTypes;
import com.belafon.Game.Maps.Place.ListOfAllTypesOfPlaces;
import com.belafon.Game.Maps.Resources.ListOfAllTypesOfResources;
import com.belafon.Server.Server;

public class App {
	public static void main(String[] args) {
		ListOfAllTypesOfResources.setUpAllResources();
		ListOfAllTypesOfPlaces.setUpAllTypesOfPlaces();
        ListOfAllItemTypes.setUpItems();

        
        new Thread(new Runnable(){

			@Override
			public void run() {
				Thread.currentThread().setName("ClientStart");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                for (int i = 0; i < 1; i++) {
                    new Client();                    
                }
			}
			
		}).start();
        new Server();
	}
}
