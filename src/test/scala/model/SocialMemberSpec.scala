package com.flurdy.socialcrowd.model

import org.specs2.mutable._
import org.specs2.specification._
import org.joda.time.DateTime
import com.flurdy.socialcrowd.model._


class SocialMemberSpec extends Specification {

   "Social Member" should {

      "post action are stored in posts" in {
         val member = new SocialMember("Alice")
         
         member.getPosts must be empty

         member.post("Hello world")

         member.getPosts must not be empty
      }
      
      "follow action adds friend to friends" in {
         val member = new SocialMember("Alice")
         val friend = new SocialMember("Peter")

         member.friends must be empty

         member.follow(friend)

         member.friends must not be empty
      }
      
      "read posts action responds with members post" in {
         val member = new SocialMember("Alice")

         member.post("Hello World")
         val output = member.getPosts.map(_.toString)

         output must be contain("Hello World (now)")
      }
      
      "return all posts" in {
         val member = new SocialMember("Alice")

         member.post("Hello World")
         member.post("Hello Another World")
         member.post("Hello Third World")

         val output = member.getPosts.map(_.toString)

         output must have length(3)
         output must be contain("Hello World (now)")
         output must be contain("Hello Another World (now)")
         output must be contain("Hello Third World (now)")
      }
      
      "return ordered posts" in {
         val member = new SocialMember("Alice")
         member.postMessage(SocialMessage("Hello World", DateTime.now.minusSeconds(10)))
         member.postMessage(SocialMessage("Hello Third World", DateTime.now.minusMinutes(10)))
         member.postMessage(SocialMessage("Hello Another World", DateTime.now.minusSeconds(50)))
         
         val output = member.getPosts.map(_.toString)

         output(0) must be equalTo("Hello World (10 seconds ago)")
         output(1) must be equalTo("Hello Another World (50 seconds ago)")
         output(2) must be equalTo("Hello Third World (10 minutes ago)")
      }
      
      "wall action responds with members and friends posts" in {
         val member = new SocialMember("Alice")
         val friend = new SocialMember("Peter")

         member.follow(friend)
         member.post("Hello World")
         friend.post("Hello Sun")
         val output = member.showWall.map(_.toString)

         output must have length(2)
         output must be contain("Alice - Hello World")
         output must be contain("Peter - Hello Sun")
      }
      
      "wall responds with ordered members and friends posts" in {
         val member = new SocialMember("Alice")
         val friend = new SocialMember("Peter")

         member.follow(friend)
         member.postMessage(SocialMessage("Hello World", DateTime.now.minusSeconds(10)))
         member.postMessage(SocialMessage("Hello Third World", DateTime.now.minusMinutes(10)))
         member.postMessage(SocialMessage("Hello Another World", DateTime.now.minusSeconds(50)))
         friend.postMessage(SocialMessage("Friend World", DateTime.now.minusSeconds(15)))
         friend.postMessage(SocialMessage("Friend Third World", DateTime.now.minusMinutes(9)))
         
         val output = member.showWall.map(_.toString)

         output must have length(5)
         output(0) must be equalTo("Alice - Hello World (10 seconds ago)")
         output(1) must be equalTo("Peter - Friend World (15 seconds ago)")
         output(4) must be equalTo("Alice - Hello Another World (10 minutes ago)")
      }

   }

}
