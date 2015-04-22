package com.flurdy.socialcrowd

import org.mockito.Mockito._
import org.specs2.mutable._
import org.specs2.specification._
import org.specs2.mock.Mockito
import com.flurdy.socialcrowd.output._


class SocialCrowdSpec extends Specification with Mockito {

   "Social Crowd" should {

      "process input and call output" in {
         val output      = mock[CrowdOutput]
         val socialCrowd = new SocialCrowd(output)

         socialCrowd.processInput("Hello World")

         there was one(output).printLine(anyString)
      }

      "not allow empty input" in {
         val output      = mock[CrowdOutput]
         val socialCrowd = new SocialCrowd(output)

         socialCrowd.processInput("")

         there was one(output).printLine("Input not recognised")
      }

   }
}
