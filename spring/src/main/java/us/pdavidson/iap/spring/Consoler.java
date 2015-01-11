package us.pdavidson.iap.spring;

import org.springframework.context.ApplicationContext;

import java.io.InputStream;
import java.util.Scanner;

class Consoler {

    private final InputStream in;
    private final ApplicationContext applicationContext;

    public Consoler(InputStream in, ApplicationContext applicationContext){
        this.in = in;
        this.applicationContext = applicationContext;
    }


    public void listen() {
        final Scanner scanner = new Scanner(in);
        while(scanner.hasNext()){
            String source = scanner.nextLine();
            applicationContext.publishEvent(new ConsolerEvent(source));
        }
    }

}
