package us.pdavidson.iap.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Autowired
    private ApplicationContext applicationContext;

    @Bean Consoler consoler() {
        return new Consoler(System.in, applicationContext);
    }

    @Bean
    HelloListener helloListener() {
        return new HelloListener(System.out);
    }

    @Bean WorldListener worldListener() {
        return new WorldListener(System.out);
    }
}
