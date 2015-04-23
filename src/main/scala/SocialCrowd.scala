package com.flurdy.socialcrowd

import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.model._
import com.flurdy.socialcrowd.infrastructure._
import scala.collection.mutable.{HashMap => MutableHashMap}
// import util.matching._

class SocialCrowd(output: CrowdOutput, repository: SocialMemberRepository) {

   def processInput(input: String){
      input.split("\\s+").toList match {
         case Nil     => output.printLine("Input not recognised")
         case "" :: _ => output.printLine("Input not recognised")
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
         case Nil           => member.getPosts.map(_.toString)
         case "wall" :: Nil => member.showWall.map(_.toString)
         case "->" :: tail  => {
            val message = tail.mkString(" ")
            member.post(message)
            Nil
         }
         case "follow" :: friendName :: Nil => {
            val friend = repository.findOrCreateMember(friendName)
            member.follow(friend)
            Nil
         }
         case _             => "Input not recognised" :: Nil
      }
   }

}
