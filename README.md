
Social Crowd
======


A command line social messaging and feed framework.

* Members can post messages
* Follow other friends
* Read their friends messages
* Read a 'wall' feed of messages
** Wall includes their own messages
** And messages from the friends they follow
** Messages are sorted by time and date



Prerequisites
-----

* Java 1.7
* Or Docker


Build Application
---

   ./activator assembly


Run Application
---

Start application locally with

   ./socialcrowd

Or with Docker

   docker run -ti --rm . 


Post Messages
-----

   Alice -> Hello world

   Peter -> Hello me


Follow Friends
-----

   Alice follow Peter


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
* No emoji, or odd UTF encoding
* No multiline input
* Names can not have space or characters in them









