package com.flurdy.socialcrowd.model

import org.specs2.mutable._
import org.specs2.specification._
import org.joda.time.DateTime
import com.flurdy.socialcrowd.model._


class SocialMessageSpec extends Specification { 

   "SocialMessage" should {

      "posts now" in {
         val then    = DateTime.now
         val message = SocialMessage("Hello World",then) 
         val output  = message.toString
         output must be contain("Hello World (now)")
      }

      "posts very recently" in {
         val then    = DateTime.now.minusMillis(10)
         val message = SocialMessage("Hello World",then) 
         val output  = message.toString
         output must be contain("Hello World (now)")
      }
      
      "posts seconds ago" in {
         val then    = DateTime.now.minusSeconds(2)
         val message = SocialMessage("Hello World",then) 
         val output  = message.toString
         output must be contain("Hello World (2 seconds ago)")
      }
      
      "posts minutes ago" in {
         val then    = DateTime.now.minusMinutes(3)
         val message = SocialMessage("Hello World",then) 
         val output  = message.toString
         output must be contain("Hello World (3 minutes ago)")
      }
      
      "posts days ago" in {
         val then    = DateTime.now.minusDays(5)
         val message = SocialMessage("Hello World",then) 
         val output  = message.toString
         output must be contain("Hello World (5 days ago)")
      }

   }

}
