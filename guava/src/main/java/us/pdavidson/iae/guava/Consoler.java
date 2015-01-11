package us.pdavidson.iae.guava;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.InputStream;
import java.util.Scanner;

@Singleton
class Consoler {
    private final InputStream in;
    private final EventBus eventBus;


    @Inject
    public Consoler(InputStream in, EventBus eventBus){
        this.in = in;
        this.eventBus = eventBus;
    }


    public void listen() {
        final Scanner scanner = new Scanner(in);
        while(scanner.hasNext()){
            String source = scanner.nextLine();
            eventBus.post(source);
        }
    }
}
