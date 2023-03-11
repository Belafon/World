package com.belafon.Game.Time;

import com.belafon.Console.ConsolePrint;

public class Clocks extends Thread{
    public volatile long time = 0;
    public final long delay = 33;
    @Override
    public void run() {
        Thread.currentThread().setName("Clocks");
		ConsolePrint.serverInfo("Clocks:run: lets start to count the time...");
        while(true){
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                if (time == Long.MAX_VALUE) {
                    ConsolePrint.error_big("Clocks have overflowed!");
                    throw new Error(e);
                }
            }
            time++;
        }
    }
    public long ticksToMillis(long ticks) {
        return ticks * delay;
    }
}