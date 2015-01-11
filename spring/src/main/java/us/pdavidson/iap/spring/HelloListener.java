package us.pdavidson.iap.spring;

import org.springframework.context.ApplicationListener;

import java.io.PrintStream;

 class HelloListener implements ApplicationListener<ConsolerEvent> {
    private final PrintStream out;

    public HelloListener(PrintStream out) {
        this.out = out;
    }


     @Override
     public void onApplicationEvent(ConsolerEvent event) {
         String source = (String) event.getSource();
         if (source.toLowerCase().contains("hello")){
             out.println("HelloListener: " + source);
         }
     }
}
