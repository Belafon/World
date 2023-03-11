package Game.Time;

import Console.ConsolePrint;
import Game.World;
import Game.Calendar.Events.Event;
import Server.Server;

public class CalendaryLoop  implements Runnable{
    private World game;
    public Thread loopThread;

    public CalendaryLoop(World game){
        this.game = game;
    }

    public volatile boolean logLoopThread = false;

    @Override
    public void run() {
        loopThread = Thread.currentThread();
        Thread.currentThread().setName("CalendaryLoop");
        while(game.isRunning){
            Thread.interrupted();
            Event nextEvent = game.calendar.getNextEvent();
            if(nextEvent == null){
                try { // calendar is empty, lets just wait
                    if(!Thread.interrupted())Thread.sleep(Long.MAX_VALUE);
                    else continue;
                } catch (InterruptedException e) {
                    continue;
                }

            } else {
                long durationOfSleep = nextEvent.getTimeToWait(Server.clocks, game.time);
                if(logLoopThread) ConsolePrint.gameInfo("Loop is waiting for ... " + nextEvent.getClass().getSimpleName() + " duration of sleep = " + durationOfSleep);
                if (durationOfSleep > 0) {
                    try {
                        if(!Thread.interrupted())Thread.sleep(durationOfSleep);
                        else continue;
                    } catch (InterruptedException e) {
                        continue;
                    }
                }
                if(nextEvent == game.calendar.getNextEvent() && game.calendar.heap.peek().getDate() <= game.time.getTime()){
                    if(logLoopThread) ConsolePrint.gameInfo("done " + game.calendar.heap.peek().getDate() + " " + game.time.getTime() + " " + (game.calendar.heap.peek().getDate() - game.time.getTime()));
                    game.calendar.check();
                }
            }
        }
    }
}
