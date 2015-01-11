package us.pdavidson.iap.spring;

import org.springframework.context.ApplicationEvent;

public class ConsolerEvent extends ApplicationEvent {
    public ConsolerEvent(String source) {
        super(source);
    }
}
