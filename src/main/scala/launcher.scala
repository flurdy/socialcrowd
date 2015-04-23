package com.flurdy.socialcrowd

import io.Source
import util.control.Breaks._
import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.infrastructure._

object InputReader {

   def readAndProcessInput(socialProcessor: SocialProcessor) = {
      breakable {
         print("> ")
         for(line <- Source.stdin.getLines){
            if(line == "" || line == "."|| line == "exit") break
            else {
               socialProcessor.processInput(line)
               print("> ")
            }
         }
      }
   }

}

object Launcher extends App {

   private val crowdOutput      = new StandardCrowdOutput
   private val memberRepository = new InMemoryMemberRepository
   private val socialProcessor  = new SocialProcessor(crowdOutput,memberRepository)
   
   println("Welcome to Social Crowd")
   println("Enter a blank line or . to finish")

   InputReader.readAndProcessInput(socialProcessor)

   println("End of Social Crowd")

}
