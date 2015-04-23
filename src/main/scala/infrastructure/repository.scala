package com.flurdy.socialcrowd.infrastructure

import com.flurdy.socialcrowd.model._
import scala.collection.mutable.{HashMap => MutableHashMap}


trait SocialMemberRepository {
   
   def findMember(memberName: String): Option[SocialMember] 

   def createMember(memberName: String): SocialMember 

   def findOrCreateMember(memberName: String): SocialMember 

}

class InMemoryMemberRepository extends SocialMemberRepository {

   private val members = new MutableHashMap[String,SocialMember]

   def findMember(memberName: String): Option[SocialMember] = members.get(memberName.toLowerCase)

   def createMember(memberName: String): SocialMember = {
      val member = SocialMember(memberName)
      members += memberName.toLowerCase -> member
      member
   }

   def findOrCreateMember(memberName: String): SocialMember = {
      findMember(memberName).getOrElse(createMember(memberName))
   }

}
