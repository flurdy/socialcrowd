package com.flurdy.socialcrowd

import io.Source
import util.control.Breaks._
import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.infrastructure._

object InputReader {

   def readAndProcessInput(socialCrowd: SocialCrowd) = {
      breakable {
         for(line <- Source.stdin.getLines){
            if(line == "" || line == ".") break
            else socialCrowd.processInput(line)
         }
      }
   }

}

object Launcher extends App {

   private val crowdOutput      = new StandardCrowdOutput
   private val memberRepository = new InMemoryMemberRepository
   private val socialCrowd      = new SocialCrowd(crowdOutput,memberRepository)
   
   println("Welcome to Social Crowd")

   InputReader.readAndProcessInput(socialCrowd)

   println("End of Social Crowd")

}
