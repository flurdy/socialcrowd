package com.flurdy.socialcrowd

import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.model._
import com.flurdy.socialcrowd.infrastructure._
import scala.collection.mutable.{HashMap => MutableHashMap}
// import util.matching._

class SocialProcessor(output: CrowdOutput, repository: SocialMemberRepository) {

   def processInput(input: String){
      input.split("\\s+").toList match {
         case Nil | "" :: _ => {}
         case memberName :: tail => {
            val member = repository.findOrCreateMember(memberName)
            for ( line   <- processAction(member,tail) ){
               output.printLine(line)
            }
         }
      }
   }

   def processAction(member: SocialMember, tail: List[String]): List[String] = {
      tail match {
         case Nil           => member.getPosts.map(_.messagePost)
         case "wall" :: Nil => member.showWall.map(_.wallPost)
         case "->" :: tail  => {
            val message = tail.mkString(" ")
            member.post(message)
            Nil
         }
         case "follows" :: friendName :: Nil => {
            if(friendName.toLowerCase != member.memberName.toLowerCase) {
               val friend = repository.findOrCreateMember(friendName)
               member.follows(friend)            
               Nil
            } else {
               "Error: Ignored following yourself" :: Nil
            }
         }
         case _  => "Error: Action not recognised" :: Nil
      }
   }

}
