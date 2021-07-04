package admin

import java.sql.{Date, Timestamp}
import java.time.Instant

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import utils.PostgresSupport

class QuizTable(tag: Tag) extends Table[quiz](tag, Some("quiz"), "quiz") {

  def * : ProvenShape[quiz] = (id, name, startDate, endDate, activeFlag, createdOn, createdBy,modifiedOn,modifiedBy) <> (quiz.tupled, quiz.unapply)

  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("name")
  def startDate: Rep[Date] = column[Date]("start_date")
  def endDate: Rep[Date] = column[Date]("end_date")
  def activeFlag: Rep[Boolean] = column[Boolean]("activeflag")
  def createdOn: Rep[Timestamp] = column[Timestamp]("createdon")
  def createdBy: Rep[String] = column[String]("createdby")
  def modifiedOn: Rep[Timestamp] = column[Timestamp]("modifiedon")
  def modifiedBy: Rep[String] = column[String]("modifiedby")
}


object QuizDAO {

  lazy val QuizTableObj = TableQuery[QuizTable]


  def insertQuiz(email: String,name: String,startDate: Date,endDate: Date) = {
    val action = QuizTableObj.map(m => (m.name,m.startDate,m.endDate, m.activeFlag, m.createdOn, m.createdBy)) returning QuizTableObj.map( _.id) += (name,startDate,endDate,true,Timestamp.from(Instant.now()),email)
    PostgresSupport.db.run(action)
  }



}
