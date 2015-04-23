package com.flurdy.socialcrowd.output

import org.mockito.Mockito._
import org.specs2.mutable._
import org.specs2.specification._
import org.specs2.mock.Mockito
import com.flurdy.socialcrowd.output._
import com.flurdy.socialcrowd.infrastructure._
import com.flurdy.socialcrowd.model._


class StandardCrowdOutputSpec extends Specification with Mockito {

   "Social Crowd" should {

      "retain all printLines" in {
         val output = new StandardCrowdOutput

         output.printLine("Hello World")
         output.printLine("Hello Another World")
         output.printLine("Hello Third World")

         val lines = output.getPreviousLines

         lines must have length(3)
         lines must be contain("Hello World")
         lines must be contain("Hello Another World")
         lines must be contain("Hello Third World")
      }

   }
}
