package us.pdavidson.iae.guava;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.InputStream;
import java.io.PrintStream;

class Application {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Application.Module());
        Consoler consoler = injector.getInstance(Consoler.class);
        System.out.println("Starting up consoler...");
        System.out.println("Ready for Input:");
        consoler.listen();
    }


    public static class Module extends AbstractModule {

        @Override
        protected void configure() {
            bind(InputStream.class).toInstance(System.in);
            bind(PrintStream.class).toInstance(System.out);
            bind(Consoler.class);
            bind(HelloWorldListener.class).asEagerSingleton();

            final EventBus eventBus = new EventBus("my event bus");
            bind(EventBus.class).toInstance(eventBus);
        }


    }
}
