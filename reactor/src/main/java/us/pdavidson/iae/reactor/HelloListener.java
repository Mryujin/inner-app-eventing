package us.pdavidson.iae.reactor;

import reactor.event.Event;
import reactor.function.Consumer;

import java.io.PrintStream;


class HelloListener  implements Consumer<Event<String>> {
   private final PrintStream out;

   public HelloListener(PrintStream out) {
       this.out = out;
   }

    @Override
    public void accept(Event<String> stringEvent) {
        String source = stringEvent.getData();
        if (source.toLowerCase().contains("hello")){
            out.println("HelloListener: " + source + " - " + Thread.currentThread().getName());
        }
    }
}
