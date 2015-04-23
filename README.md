
Social Crowd
======


A command line social messaging and feed framework.

* Members can post messages
* Follow other friends
* Read a member's messages
* Read a member's 'wall', a feed of messages
* * Wall includes the member's own messages
* * And messages from the friends they follow
* * Messages are sorted by time and date


Prerequisites
-----

* Java 1.7
* Or Docker


Build Application
---

   ./activator assembly

Or with Docker
   
   docker build -t flurdy/socialcrowd:0.1 .


Run Application
---

Start application locally with

   ./socialcrowd

Or with Docker

   docker run -ti --rm flurdy/socialcrowd:0.1 


Post Messages
-----

   Alice -> Hello world

   Peter -> Hello me


Follow Friends
-----

   Alice follows Peter


Read Alice's Messages
-----

   Alice 

   > Hello World (10 seconds ago)


Read Wall
-----

   Alice wall

   > Peter - Hello (1 minute ago)

   > Alice - Hello World (2 minutes ago)


Assumptions
-----

* Happy sunny day input only
* Upper & lower case irrelevant
* No emoji or odd UTF encoding
* No multiline input
* Names can not have white space characters in them


Improvements
----

* Cleaner Launcher and SocialProcessor classes
* Either or Scalaz validation 
* Akka if needs to scale horizontally and reactive
* Akka streams if posts/wall are large 
* Separate apps for input layer, reading and member posts persistance
* Test Launcher and InputReader
* Test with ScalaCheck
* Use injection via cake pattern, ReaderT, etc.
* Etc






