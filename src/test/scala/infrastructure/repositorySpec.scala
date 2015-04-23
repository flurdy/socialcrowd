package com.flurdy.socialcrowd.infrastructure

import org.specs2.mutable._
import org.specs2.specification._
import com.flurdy.socialcrowd.model._


class InMemoryMemberRepositorySpec extends Specification { 

   "InMemoryMemberRepository" should {

      "not find non existent members" in {
         val repository = new InMemoryMemberRepository

         val member = repository.findMember("Not Me")

         member must be none
      }

      "find existent members" in {
         val repository = new InMemoryMemberRepository

         repository.createMember("Me")
         val member = repository.findMember("Me")

         member must be some
      }

      "find or create does create members" in {
         val repository = new InMemoryMemberRepository

         val member = repository.findOrCreateMember("Me")

         member.memberName must beEqualTo("Me")
      }

   }

}
