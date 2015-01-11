package us.pdavidson.iae.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncApplication {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AsyncApplication.AsyncModule());
        Consoler consoler = injector.getInstance(Consoler.class);
        System.out.println("Starting up consoler w/ Async EventBus...");
        System.out.println("Ready for Input:");
        consoler.listen();
    }


    public static class AsyncModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(InputStream.class).toInstance(System.in);
            bind(PrintStream.class).toInstance(System.out);
            bind(Consoler.class);
            bind(HelloWorldListener.class).asEagerSingleton();

            ExecutorService executorService = Executors.newFixedThreadPool(2);

            final EventBus eventBus = new AsyncEventBus("my event stream", executorService);
            bind(EventBus.class).toInstance(eventBus);
        }


    }
}
