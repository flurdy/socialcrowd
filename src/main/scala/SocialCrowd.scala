package com.flurdy.socialcrowd

import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.model._
import scala.collection.mutable.{HashMap => MutableHashMap}
// import util.matching._

class SocialCrowd(output: CrowdOutput) {

   def processInput(input: String){
      input.split("\\s+").toList match {
         case Nil     => output.printLine("Input not recognised")
         case "" :: _ => output.printLine("Input not recognised")
         case memberName :: tail => {
            for ( line   <- SocialMember.findOrCreateMember(memberName).processAction(tail) ){
               output.printLine(line)
            }
         }
      }
   }

}
