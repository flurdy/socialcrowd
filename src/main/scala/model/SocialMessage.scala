package com.flurdy.socialcrowd.model

import org.joda.time.{DateTime,Period}
import org.joda.time.format.PeriodFormatterBuilder


object SocialMessage {

   private val formatter = new PeriodFormatterBuilder().
      appendSeconds().appendSuffix(" seconds ago").
      appendMinutes().appendSuffix(" minutes ago").
      appendHours().appendSuffix(" hours ago").
      appendDays().appendSuffix(" days ago").
      printZeroNever().
      toFormatter();

   def relativeFormat(then: DateTime): String = {
      val now = DateTime.now
      if(now.minusSeconds(1).isBefore(then)) "now"
      else formatter.print(new Period(then,now))
   }

}

case class SocialMessage(message: String, timestamp: DateTime){

   def this(message: String) = this(message, DateTime.now)
   
   def toFormattedTimestamp = SocialMessage.relativeFormat(timestamp)
   
   override def toString = s"$message ($toFormattedTimestamp)"

}
