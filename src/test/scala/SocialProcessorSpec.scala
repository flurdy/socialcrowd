package com.flurdy.socialcrowd

import org.mockito.Mockito._
import org.specs2.mutable._
import org.specs2.specification._
import org.specs2.mock.Mockito
import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.infrastructure._
import com.flurdy.socialcrowd.model._


class SocialProcessorSpec extends Specification with Mockito {

   "SocialProcessor" should {

      "process input and call output" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val processor   = new SocialProcessor(output,repository)

         processor.processInput("Hello World")

         there was one(output).printLine(anyString)
      }

      "membername action looks for member posts" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val member      = mock[SocialMember]
         val processor   = new SocialProcessor(output,repository)
         repository.findOrCreateMember(anyString) returns member
         member.getPosts returns List.empty         

         processor.processInput("Alice")

         there was one(repository).findOrCreateMember("Alice")
         there was one(member).getPosts
      }

      "follows friend looks for friend" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val member      = new SocialMember("Alice")
         val processor   = new SocialProcessor(output,repository)
         repository.findOrCreateMember("Alice") returns member

         processor.processInput("Alice follows Peter")

         there was one(repository).findOrCreateMember("Peter")
      }

      "follows action adds friend to member" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val member      = mock[SocialMember]
         val friend      = new SocialMember("Peter")
         val processor   = new SocialProcessor(output,repository)
         repository.findOrCreateMember("Alice") returns member
         repository.findOrCreateMember("Peter") returns friend
         member.memberName returns "Alice"

         processor.processInput("Alice follows Peter")

         there was one(member).follows(any[SocialMember])
      }

      "follows action without friends name does not add any friend" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val member      = mock[SocialMember]
         val processor   = new SocialProcessor(output,repository)
         repository.findOrCreateMember("Alice") returns member
         member.memberName returns "Alice"

         processor.processInput("Alice follows")

         there was no(member).follows(any[SocialMember])
      }

      "follows yourself is ignored" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val member      = mock[SocialMember]
         val processor   = new SocialProcessor(output,repository)
         repository.findOrCreateMember("Alice") returns member
         member.memberName returns "Alice"

         processor.processInput("Alice follows Alice")

         there was no(member).follows(any[SocialMember])
         there was one(output).printLine("Error: Ignored following yourself")
      }

      "wall action looks for member and friends wall posts" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val member      = mock[SocialMember]
         val processor   = new SocialProcessor(output,repository)
         repository.findOrCreateMember(anyString) returns member
         member.memberName returns "Alice"
         member.showWall returns List.empty         

         processor.processInput("Alice wall")

         there was one(member).showWall
      }

      "-> action call member post" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val member      = mock[SocialMember]
         val processor   = new SocialProcessor(output,repository)
         repository.findOrCreateMember("Alice") returns member    

         processor.processInput("Alice -> Hello")

         there was one(member).post(anyString)
      }

      "-> action combines words" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val member      = mock[SocialMember]
         val processor   = new SocialProcessor(output,repository)
         repository.findOrCreateMember("Alice") returns member    

         processor.processInput("Alice -> Hello world and other places")

         there was one(member).post("Hello world and other places")
      }

      "ignore empty input" in {
         val output      = mock[CrowdOutput]
         val repository  = mock[SocialMemberRepository]
         val processor = new SocialProcessor(output,repository)

         processor.processInput("")

         there was no(output).printLine(anyString)
         there was no(repository).findOrCreateMember(anyString)
      }

   }
}
