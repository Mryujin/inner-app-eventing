# A simple comparison of Inner App Eventing.

# The idea
Each app can be run from an Application main class.
Each app should print the following

out: Starting up consoler...
out: Ready for Input:

in:  Hello Jimmy
out: HelloListener: Hello Jimmy - :Thread-Name

in:  Waynes World
out: WorldListener: Waynes World - :Thread-Name

in:  Hello World
out: HelloListener: Hello World - :Thread-Name
out: WorldListener: Hello World - :Thread-Name

# Spring
## Benefits
Built into spring since spring 3.  
Rather easy to build a subscriber and emit events.
## Possible Downsides
Producer must know about the application context, but since we're already using spring, it's not really unrealistic for a bean to need visibility.  
Listener can only subscribe to a single application event
Listeners run in same thread, but if perf is an issue, listener could execute slow operation in new tread
Used internally, so it gets Spring Framework Events

# Guava
## Benefits
Easy.
Asynch version
## Possible Downsides
Listeners must register with the EventBus, by default they must have knowledge of the event bus, but realistically, you're going to have an EventBus that is di'd

# Spring Messaging
## Benefits
Built into Spring 4
Should be easy to extend into spring integration if needed intra-app communication is needed
Asych or Synch
## Possible Downsides
Listeners subscribe to the MessageChannel, it was pretty easy to inject the MessageChannel in the Constructor

# Reactor
## Benefits
Seems easy - about as straight forward as the others
Works either by registering the bean with the Reactor or @Consumer
Seems the most flexible, since you have the reactive platform.
## Possible Downsides
Documentation Sucks - I had to read thru the source code to figure out how to make the Annotation @Consumer work.
