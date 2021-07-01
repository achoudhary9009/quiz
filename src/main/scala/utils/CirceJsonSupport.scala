package utils

import java.sql.{Date, Timestamp}
import java.text.SimpleDateFormat

import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}

import scala.util.Try

trait CirceJsonSupport {

  //Create a date formatter for ISO date string
  private val localIsoDateFormatter = new ThreadLocal[SimpleDateFormat] {
    override def initialValue() = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  }

  //convert timestamp to ISO string
  private def timestampToIsoString(timestamp: Timestamp) = {
    localIsoDateFormatter.get().format(timestamp)
  }

  //to convert date to ISO string
  private def dateToIsoString(date: Date) = {
    localIsoDateFormatter.get().format(date)
  }

  //To parse a ISO string to date
  private def parseIsoDateString(date: String): Option[Date] =
    Try {
      new Date(localIsoDateFormatter.get().parse(date).getTime)
    }.toOption

  //Custom timestamp formatter for marshalling and unmarshalling
  implicit val TimestampFormat: Encoder[Timestamp] with Decoder[Timestamp] = new Encoder[Timestamp] with Decoder[Timestamp] {
    override def apply(a: Timestamp): Json = Encoder.encodeString.apply(timestampToIsoString(a))

    override def apply(c: HCursor): Result[Timestamp] = Decoder.decodeLong.map(s => new Timestamp(s)).apply(c)
  }

  //Custom date formatter for marshalling and unmarshalling
  implicit val DateFormat: Encoder[Date] with Decoder[Date] = new Encoder[Date] with Decoder[Date] {
    override def apply(a: Date): Json = Encoder.encodeString.apply(dateToIsoString(a))

    override def apply(c: HCursor): Result[Date] = Decoder.decodeString.map(s => parseIsoDateString(s).get).apply(c)
  }

}
