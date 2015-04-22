package com.flurdy.socialcrowd

import io.Source
import util.control.Breaks._
import com.flurdy.socialcrowd.output._

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

   val crowdOutput = new StandardCrowdOutput
   val socialCrowd = new SocialCrowd(crowdOutput)
   
   println("Welcome to Social Crowd")

   InputReader.readAndProcessInput(socialCrowd)

   println("End of Social Crowd")

}
