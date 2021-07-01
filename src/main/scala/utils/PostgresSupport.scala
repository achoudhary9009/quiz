package utils

import slick.jdbc.PostgresProfile.api._
import utils.PostgresSupport.db

object PostgresSupport {
  lazy val db = Database.forConfig("quizDatabase")

}
trait CreateSession {
  implicit val session: Session = db.createSession()
}