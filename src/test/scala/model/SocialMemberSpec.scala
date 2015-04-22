package com.flurdy.socialcrowd

// import org.mockito.Mockito._ 
import org.specs2.mutable._
import org.specs2.specification._
// import org.specs2.mock.Mockito
// import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.model._


class SocialMemberSpec extends Specification { //with Mockito {

   "Social Member" should {

      "post action does not send anything to output" in {
         val member = new SocialMember("Alice")

         val output = member.post("Hello world")

         output must be empty
      }
      
      "follow action does not send anything to output" in {
         val member = new SocialMember("Alice")

         val output = member.follow("Peter")

         output must be empty
      }
      
      "read posts action responds with members post" in {
         val member = new SocialMember("Alice")

         member.post("Hello World")
         val output = member.showPosts

         output must be contain("Hello World")
      }
      
      "wall action responds with members and friends posts" in {
         val member = new SocialMember("Alice")
         val friend = new SocialMember("Peter")

         member.follow("Peter")
         member.post("Hello World")
         friend.post("Hello Sun")
         val output = member.showWall

         output must be contain("Hello World")
         output must be contain("Hello Sun")
      }

   }

}
