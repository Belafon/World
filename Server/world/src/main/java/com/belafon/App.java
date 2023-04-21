package com.belafon;

import com.belafon.server.Server;
import com.belafon.world.items.ListOfAllItemTypes;
import com.belafon.world.maps.place.ListOfAllTypesOfPlaces;
import com.belafon.world.maps.resources.ListOfAllTypesOfResources;

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
