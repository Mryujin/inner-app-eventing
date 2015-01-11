package us.pdavidson.iae.springmessing;

import org.springframework.messaging.*;

import java.io.PrintStream;

class WorldListener implements MessageHandler{
    private final PrintStream out;

    public WorldListener(PrintStream out, SubscribableChannel messageChannel) {
        this.out = out;
        messageChannel.subscribe(this);
    }


    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        if (message.getPayload() instanceof String){
            String source = (String) message.getPayload();
            if (source.toLowerCase().contains("world")){
                out.println("WorldListener: " + source +  " - " + Thread.currentThread().getName());
            }
        }
    }
}
