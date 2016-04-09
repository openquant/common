package com.larroy.quant.common

import java.time.format.DateTimeFormatter
import java.time.{ZoneId, Instant, ZonedDateTime}

import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scalaz.\/

package object utils {
  def time[R](block: => R): R = {
    val log: Logger = LoggerFactory.getLogger("timer")
    val t0 = System.currentTimeMillis()
    val result = block // call-by-name
    val t1 = System.currentTimeMillis()
    log.info("Elapsed time: " + (t1 - t0) + " ms")
    result
  }

  def epoch2Iso(x: Long): String = ZonedDateTime.ofInstant(Instant.ofEpochSecond(x), ZoneId.systemDefault()).format(DateTimeFormatter.ISO_INSTANT)

  def toPrintable(d: ZonedDateTime): String = {
    d.format(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"))
  }

  def toEpoch(d: ZonedDateTime): Long = d.toInstant.toEpochMilli

  def toISO(zonedDateTime: ZonedDateTime): String = {
    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    zonedDateTime.format(dateTimeFormatter)
  }

  def fromISO(s: String): Option[ZonedDateTime] = s match {
    case "" ⇒
      None
    case _ ⇒
      val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
      \/.fromTryCatchNonFatal {
        ZonedDateTime.parse(s, dateTimeFormatter)
      }.toOption
  }

  /**
    * Get the number of periods between start and end date at the specified resolution
    * @param start      start date
    * @param end        end date
    * @param resolution timespan
    * @return number of periods
    */
  def numPeriodsBetween(start: ZonedDateTime, end: ZonedDateTime, resolution: Resolution.Enum): Long = {
    import java.time.temporal.ChronoUnit._
    val days = Math.abs(DAYS.between(start, end))
    resolution match {
      case Resolution._1_secs =>
        Math.abs(SECONDS.between(start, end))

      case Resolution._5_secs =>
        Math.abs(SECONDS.between(start, end)) / 5

      case Resolution._10_secs =>
        Math.abs(SECONDS.between(start, end)) / 10

      case Resolution._15_secs =>
        Math.abs(SECONDS.between(start, end)) / 15

      case Resolution._30_secs =>
        Math.abs(SECONDS.between(start, end)) / 30

      case Resolution._1_min =>
        Math.abs(MINUTES.between(start, end))

      case Resolution._2_mins =>
        Math.abs(MINUTES.between(start, end)) / 2

      case Resolution._3_mins =>
        Math.abs(MINUTES.between(start, end)) / 3

      case Resolution._5_mins =>
        Math.abs(MINUTES.between(start, end)) / 5

      case Resolution._10_mins =>
        Math.abs(MINUTES.between(start, end)) / 10

      case Resolution._15_mins =>
        Math.abs(MINUTES.between(start, end)) / 15

      case Resolution._20_mins =>
        Math.abs(MINUTES.between(start, end)) / 20

      case Resolution._30_mins =>
        Math.abs(MINUTES.between(start, end)) / 30

      case Resolution._1_hour =>
        Math.abs(HOURS.between(start, end))

      case Resolution._4_hours =>
        Math.abs(HOURS.between(start, end)) / 4

      case Resolution._1_day =>
        Math.abs(DAYS.between(start, end))

      case Resolution._1_week =>
        Math.abs(DAYS.between(start, end)) / 7
    }
  }
}
