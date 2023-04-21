package com.belafon.world.time;

import com.belafon.console.ConsolePrint;

/**
 * Records the time changes in the world.
 */
public class Clocks extends Thread{
    public volatile long time = 0;
    public final long delay = 33;
    public volatile boolean isRunning = false; 
    @Override
    public void run() {
        synchronized (this) {
            if (!isRunning) {
                isRunning = true;
            } else
                return;
        }

        Thread.currentThread().setName("Clocks");
        ConsolePrint.serverInfo("Clocks:run: lets start to count the time...");
        while (true) {
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
    
    /**
     * Tick is special worlds unit. 
     * It is the smallest time period between
     * two worlds changes which are not executed 
     * in the same time. That is because the 
     * servers calendar uses it.
     * 
     */
    public long ticksToMillis(long ticks) {
        return ticks * delay;
    }
}