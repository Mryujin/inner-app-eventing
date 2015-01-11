package us.pdavidson.iae.reactor;

import reactor.event.Event;
import reactor.function.Consumer;

import java.io.PrintStream;


class WorldListener implements Consumer<Event<String>> {
    private final PrintStream out;

    public WorldListener(PrintStream out) {
        this.out = out;
    }


    @Override
    public void accept(Event<String> stringEvent) {
        String source = stringEvent.getData();
        if (source.toLowerCase().contains("world")){
            out.println("WorldListener: " + source +  " - " + Thread.currentThread().getName());
        }
    }
}
