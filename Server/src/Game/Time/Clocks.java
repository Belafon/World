package Game.Time;

import Console.ConsolePrint;

public class Clocks extends Thread{
    public volatile int time = 0;
    public final int delay = 100;
    @Override
    public void run() {
        Thread.currentThread().setName("Clocks");
		ConsolePrint.serverInfo("Clocks:run: lets start to count the time...");
        while(true){
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {}
            time++;
        }
    }
    public int ticksToMillis(int ticks) {
        return ticks * delay;
    }
}