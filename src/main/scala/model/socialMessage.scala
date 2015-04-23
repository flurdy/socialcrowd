package com.flurdy.socialcrowd.model

import org.joda.time.{DateTime,Period}
import org.joda.time.format.PeriodFormatterBuilder


object RelativeTime {

   private val secondsFormatter = new PeriodFormatterBuilder().
         appendSeconds().appendSuffix(" seconds ago").toFormatter();
   private val minutesFormatter = new PeriodFormatterBuilder().
         appendMinutes().appendSuffix(" minutes ago").toFormatter();
   private val hoursFormatter = new PeriodFormatterBuilder().
         appendHours().appendSuffix(" hours ago").toFormatter();
   private val daysFormatter = new PeriodFormatterBuilder().
         appendDays().appendSuffix(" days ago").toFormatter();

   def format(then: DateTime): String = {
      val now = DateTime.now
      if(now.minusSeconds(1).isBefore(then))      "now"
      else if(now.minusMinutes(1).isBefore(then)) secondsFormatter.print(new Period(then,now))
      else if(now.minusHours(1).isBefore(then))   minutesFormatter.print(new Period(then,now))
      else if(now.minusDays(1).isBefore(then))    hoursFormatter.print(new Period(then,now))
      else daysFormatter.print(new Period(then,now))
   }

}

class SocialMessage(memberName: String, message: String, val timestamp: DateTime) extends Ordered[SocialMessage] {

   def this(memberName: String, message: String) = this(memberName, message, DateTime.now)
   
   def toFormattedTimestamp = RelativeTime.format(timestamp)
   
   def messagePost = s"$message ($toFormattedTimestamp)"

   def wallPost    = s"$memberName - $messagePost"

   def compare(that: SocialMessage) = {
      if(that.timestamp.isBefore(timestamp)) -1
      else if(that.timestamp.isAfter(timestamp)) 1
      else 0
   }

   override def toString = wallPost

}
