package us.pdavidson.iae.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

import java.io.PrintStream;

import static reactor.event.selector.Selectors.$;

@Configuration
@EnableReactor
@ComponentScan
class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);

        Consoler consoler = context.getBean(Consoler.class);
        System.out.println("Starting up consoler...");
        System.out.println("Ready for Input:");
        consoler.listen();
    }

    @Autowired
    Environment environment;

    @Bean
    Reactor reactor() {
        return Reactors.reactor()
                .env(environment)
                .dispatcher(Environment.RING_BUFFER)
                .get();
    }

    @Bean Consoler consoler() {
        return new Consoler(System.in, reactor());
    }

    @Bean
    HelloListener helloListener() {
        HelloListener helloListener = new HelloListener(System.out);
        reactor().on($("topic"), helloListener);
        return helloListener;

    }

    @Bean WorldListener worldListener() {
        WorldListener worldListener = new WorldListener(System.out);
        reactor().on($("topic"), worldListener);
        return worldListener;
    }

    @Bean
    PrintStream out() {
        return System.out;
    }
}
