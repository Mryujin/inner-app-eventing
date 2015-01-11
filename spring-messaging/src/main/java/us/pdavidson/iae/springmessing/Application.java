package us.pdavidson.iae.springmessing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.ExecutorSubscribableChannel;

import java.util.concurrent.Executors;

@Configuration
class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);

        Consoler consoler = context.getBean(Consoler.class);
        System.out.println("Starting up consoler...");
        System.out.println("Ready for Input:");
        consoler.listen();
    }

    @Bean
    SubscribableChannel messageChannel() {
        return new ExecutorSubscribableChannel(Executors.newFixedThreadPool(3));
    }

    @Bean Consoler consoler() {
        return new Consoler(System.in, messageChannel());
    }

    @Bean
    HelloListener helloListener() {
        return new HelloListener(System.out, messageChannel());
    }

    @Bean WorldListener worldListener() {
        return new WorldListener(System.out, messageChannel());
    }
}
