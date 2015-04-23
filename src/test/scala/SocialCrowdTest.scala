package com.flurdy.socialcrowd

import org.mockito.Mockito._
import org.specs2.mutable._
import org.specs2.specification._
import org.specs2.mock.Mockito
import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.infrastructure._
import com.flurdy.socialcrowd.model._


class SocialCrowdSpec extends Specification with Mockito {

   "Social Crowd" should {

      "process input and call output" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val socialCrowd = new SocialCrowd(output,repository)

         socialCrowd.processInput("Hello World")

         there was one(output).printLine(anyString)
      }

      "not allow empty input" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val socialCrowd = new SocialCrowd(output,repository)

         socialCrowd.processInput("")

         there was one(output).printLine("Input not recognised")
      }

   }
}
