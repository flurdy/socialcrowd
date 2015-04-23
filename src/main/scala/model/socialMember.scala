package com.flurdy.socialcrowd.model

import scala.collection.mutable.{MutableList,HashSet => MutableHashSet}


case class SocialMember(memberName: String){

   private val posts   = new MutableList[SocialMessage]
   val friends         = new MutableHashSet[SocialMember]

   def getPosts: List[SocialMessage] = posts.toList

   def showWall: List[SocialMessage] = {
      List.empty
   }

   def post(message: String) = postMessage( new SocialMessage(message) )
   
   def postMessage(message: SocialMessage) = posts += message

   def follow(friend: SocialMember) = friends += friend

}
