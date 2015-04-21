package com.flurdy.socialcrowd

import io.Source
import util.control.Breaks._


object Launcher extends App {

   val socialCrowd = new SocialCrowd

   def readInput = {
      breakable {
         for(line <- Source.stdin.getLines){
            if(line == "" || line == ".") break
            else socialCrowd.processInput(line)
         }
      }
   }
   
   println("Welcome to Social Crowd")

   readInput

   println("End of Social Crowd")

}
