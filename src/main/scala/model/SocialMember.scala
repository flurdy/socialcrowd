package com.flurdy.socialcrowd.model

import scala.collection.mutable.{MutableList,HashSet => MutableHashSet}


case class SocialMember(memberName: String){

   val posts   = new MutableList[SocialMessage]
   val friends = new MutableHashSet[SocialMember]

   def getPosts: List[SocialMessage] = posts.toList.sorted

   def showWall: List[SocialMessage] = {
      ( for{
            member <- this +: friends.toList
            post   <- member.posts  
         } yield post 
      ).sorted
   }

   def post(message: String) = postMessage( new SocialMessage(memberName,message) )
   
   def postMessage(message: SocialMessage) = posts += message

   def follows(friend: SocialMember) {
      if( friend != this ) friends += friend
   }

}
