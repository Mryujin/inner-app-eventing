# A simple comparison of Inner App Eventing.

# Spring

## 

## Benefits
Built into spring since spring 3.  
Rather easy to build a subscriber and emit events.  


## Possible Downsides
Producer must know about the application context, but since we're already using spring, it's not really unrealistic for a bean to need visibility.  
Listener can only subscribe to a single application event
Listeners run in same thread, but if perf is an issue, listener could execute slow operation in new tread



