package us.pdavidson.iae.reactor;

import reactor.core.Reactor;
import reactor.event.Event;

import java.io.InputStream;
import java.util.Scanner;

class Consoler {

    private final InputStream in;
    private final Reactor reactor;

    public Consoler(InputStream in, Reactor reactor){
        this.in = in;

        this.reactor = reactor;
    }


    public void listen() {
        final Scanner scanner = new Scanner(in);
        while(scanner.hasNext()){
            String source = scanner.nextLine();
            reactor.notify("topic", Event.wrap(source));
        }
    }

}
