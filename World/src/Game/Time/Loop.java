package Game.Time;

import Console.ConsolePrint;
import Game.Game;
import Game.Calendar.Events.Event;

public class Loop  implements Runnable{
    private Game game;
    public Thread loopThread;

    public Loop(Game game){
        this.game = game;
    }

    public volatile boolean logLoopThread = false;

    @Override
    public void run() {
        loopThread = Thread.currentThread();
        Thread.currentThread().setName("Loop");
        while(game.isRunning){
            Thread.interrupted();
            Event nextEvent = game.calendar.getNextEvent();
            if(nextEvent == null)
                try {
                    if(!Thread.interrupted())Thread.sleep(Long.MAX_VALUE);
                    else continue;
                } catch (InterruptedException e) {
                    continue;
                }
            else{
                int durationOfSleep = nextEvent.getTimeToWait(game.server.clocks, game.time);
                if(logLoopThread) ConsolePrint.gameInfo("Loop is waiting for ... " + nextEvent.getClass().getSimpleName() + " duration of sleep = " + durationOfSleep);
                if(durationOfSleep > 0)
                    try {
                        if(!Thread.interrupted())Thread.sleep(durationOfSleep);
                        else continue;
                    } catch (InterruptedException e) {
                        continue;
                    }
                if(nextEvent == game.calendar.getNextEvent() && game.calendar.heap.peek().getDate() <= game.time.getTime()){
                    if(logLoopThread) ConsolePrint.gameInfo("done " + game.calendar.heap.peek().getDate() + " " + game.time.getTime() + " " + (game.calendar.heap.peek().getDate() - game.time.getTime()));
                    game.calendar.check();
                }
            }
        }
    } 
}
