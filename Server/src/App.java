import Game.Items.ListOfAllItemTypes;
import Game.Maps.Place.ListOfAllTypesOfPlaces;
import Game.Maps.Resources.ListOfAllTypesOfResources;
import Server.Server;
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
