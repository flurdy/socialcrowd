package com.flurdy.socialcrowd

import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.model._
import com.flurdy.socialcrowd.infrastructure._
import scala.collection.mutable.{HashMap => MutableHashMap}

class SocialProcessor(output: CrowdOutput, repository: SocialMemberRepository) {

   def processInput(input: String){
      input.split("\\s+").toList match {
         case Nil | "" :: _ => {}
         case memberName :: tail => {
            val member = repository.findOrCreateMember(memberName)
            processAction(member,tail).foreach( output.printLine(_) )
         }
      }
   }

   def processAction(member: SocialMember, tail: List[String]): List[String] = {
      tail match {
         case Nil           => member.getPosts.map(_.messagePost)
         case "wall" :: _   => member.showWall.map(_.wallPost)
         case "->" :: tail  => {
            member.post(tail.mkString(" "))
            Nil
         }
         case "follows" :: friendName :: _ => {
            followFriend(member,friendName)
            Nil
         }
         case _  => "Error: Action not recognised" :: Nil
      }
   }

   private def followFriend(member: SocialMember, friendName: String) {
      repository.findMember(friendName).map( member.follows(_) )
   }

}
