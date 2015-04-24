package com.flurdy.socialcrowd.model

import org.joda.time.{DateTime,Period}
import org.joda.time.format.PeriodFormatterBuilder


object RelativeTime {

   private val secondsFormatter = new PeriodFormatterBuilder().
         appendSeconds().appendSuffix(" second ago"," seconds ago").toFormatter();
   private val minutesFormatter = new PeriodFormatterBuilder().
         appendMinutes().appendSuffix(" minute ago"," minutes ago").toFormatter();
   private val hoursFormatter = new PeriodFormatterBuilder().
         appendHours().appendSuffix(" hour ago"," hours ago").toFormatter();
   private val daysFormatter = new PeriodFormatterBuilder().
         appendDays().appendSuffix(" day ago"," days ago").toFormatter();
   private val weeksFormatter = new PeriodFormatterBuilder().
         appendWeeks().appendSuffix(" week ago"," weeks ago").toFormatter();
   private val monthsFormatter = new PeriodFormatterBuilder().
         appendMonths().appendSuffix(" month ago"," months ago").toFormatter();
   private val yearsFormatter = new PeriodFormatterBuilder().
         appendYears().appendSuffix(" year ago"," years ago").toFormatter();

   def format(then: DateTime): String = {
      val now = DateTime.now
      val relativePeriod = new Period(then,now)
      if(now.minusSeconds(1).isBefore(then))      "now"
      else if(now.minusMinutes(1).isBefore(then)) secondsFormatter.print(relativePeriod)
      else if(now.minusHours(1).isBefore(then))   minutesFormatter.print(relativePeriod)
      else if(now.minusDays(1).isBefore(then))    hoursFormatter.print(relativePeriod)
      else if(now.minusWeeks(1).isBefore(then))   daysFormatter.print(relativePeriod)
      else if(now.minusMonths(1).isBefore(then))  weeksFormatter.print(relativePeriod)
      else if(now.minusYears(1).isBefore(then))   monthsFormatter.print(relativePeriod)
      else yearsFormatter.print(relativePeriod)
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
