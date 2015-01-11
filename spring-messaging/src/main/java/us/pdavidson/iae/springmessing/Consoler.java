package us.pdavidson.iae.springmessing;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.io.InputStream;
import java.util.Scanner;

class Consoler {

    private final InputStream in;
    private final MessageChannel messageChannel;

    public Consoler(InputStream in, MessageChannel messageChannel){
        this.in = in;
        this.messageChannel = messageChannel;
    }


    public void listen() {
        final Scanner scanner = new Scanner(in);
        while(scanner.hasNext()){
            String source = scanner.nextLine();
            messageChannel.send(new GenericMessage<String>(source));
        }
    }

}
