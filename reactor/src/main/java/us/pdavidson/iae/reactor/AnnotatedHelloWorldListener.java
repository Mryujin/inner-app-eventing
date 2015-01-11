package us.pdavidson.iae.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.event.Event;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

import java.io.PrintStream;

@Consumer
public class AnnotatedHelloWorldListener {
    private final PrintStream out;

    @Autowired
    public AnnotatedHelloWorldListener(PrintStream out) {
        this.out = out;
    }


    @Selector(value="topic", reactor = "@reactor")
    public void handle(Event<String> stringEvent) {
        String source = stringEvent.getData();
        if (source.toLowerCase().contains("helloworld")){
            out.println("AnnotatedHelloWorldListener: " + source + " - " + Thread.currentThread().getName());
        }
    }

}
