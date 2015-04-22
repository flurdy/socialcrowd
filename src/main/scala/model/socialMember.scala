package com.flurdy.socialcrowd.model

import scala.collection.mutable.{HashMap => MutableHashMap}


object SocialMember {

   val members = new MutableHashMap[String,SocialMember]

   def findMember(memberName: String): Option[SocialMember] = members.get(memberName)

   def createMember(memberName: String): SocialMember = {
      val member = SocialMember(memberName)
      members += memberName -> member
      member
   }

   def findOrCreateMember(memberName: String): SocialMember = {
      findMember(memberName).getOrElse(createMember(memberName))
   }
}

case class SocialMember(memberName: String){

   def showPosts: List[String] = {
      List.empty
   }

   def showWall: List[String] = {
      List.empty
   }

   def post(message: String): List[String] = {
      Nil
   }

   def follow(friendName: String): List[String] = {
      Nil
   }

   def processAction(tail: List[String]): List[String] = {
      tail match {
         case Nil           => showPosts
         case "wall" :: _   => showWall
         case "->" :: tail  => post(tail.mkString(" "))
         case "follow" :: friendName :: _ => follow(friendName)
         case _             => "Input not recognised" :: Nil
      }
   }

}

