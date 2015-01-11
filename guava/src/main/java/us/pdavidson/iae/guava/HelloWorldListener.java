package us.pdavidson.iae.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.PrintStream;

@Singleton
public class HelloWorldListener {


    private final PrintStream out;
    private final EventBus eventBus;

    @Inject
    public HelloWorldListener(PrintStream out, EventBus eventBus) {
        this.out = out;
        this.eventBus = eventBus;

        eventBus.register(this);
    }

    public @Subscribe void onHelloEvent(String source){
        if (source.toLowerCase().contains("hello")){
            out.println("HelloListener: " + source + " - " + Thread.currentThread().getName());
        }
    }


    public @Subscribe void onWorldEvent(String source){
        if (source.toLowerCase().contains("world")){
            out.println("WorldListener: " + source + " - " + Thread.currentThread().getName());
        }
    }

}
