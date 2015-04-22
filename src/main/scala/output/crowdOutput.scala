package com.flurdy.socialcrowd.output

import scala.collection.mutable.MutableList

trait CrowdOutput {

   def printLine(line: String)

   def getPreviousLines: List[String]

}

class StandardCrowdOutput extends CrowdOutput {

   val outputLines = new MutableList[String]

   def printLine(line: String) {
      outputLines += line
      println(line)
   }

   def getPreviousLines: List[String] = outputLines.toList

}
