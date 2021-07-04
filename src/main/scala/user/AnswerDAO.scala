package user

import java.sql.Timestamp
import java.time.Instant

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import utils.PostgresSupport

class AnswerTable(tag: Tag) extends Table[Answer](tag, Some("quiz"), "user_question_answer") {

  def * : ProvenShape[Answer] = (id, questionId, email, optionA, optionB, optionC, optionD, result, activeFlag, createdOn, createdBy,modifiedOn,modifiedBy) <> (Answer.tupled, Answer.unapply)

  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def questionId: Rep[Long] = column[Long]("question_id")
  def email: Rep[String] = column[String]("email")
  def optionA: Rep[Boolean] = column[Boolean]("option_a")
  def optionB: Rep[Boolean] = column[Boolean]("option_b")
  def optionC: Rep[Boolean] = column[Boolean]("option_c")
  def optionD: Rep[Boolean] = column[Boolean]("option_d")
  def result: Rep[Boolean] = column[Boolean]("result")
  def activeFlag: Rep[Boolean] = column[Boolean]("activeflag")
  def createdOn: Rep[Timestamp] = column[Timestamp]("createdon")
  def createdBy: Rep[String] = column[String]("createdby")
  def modifiedOn: Rep[Timestamp] = column[Timestamp]("modifiedon")
  def modifiedBy: Rep[String] = column[String]("modifiedby")
}


object AnswerDAO {

  lazy val AnswerTableObj = TableQuery[AnswerTable]

  def insertAnswers(result: Array[AnswerReceive],email: String)={

    val answerSet = result.map{ans =>
      (ans.questionId,email,ans.optionA,ans.optionB,ans.optionC,ans.optionD,ans.answer.get,Timestamp.from(Instant.now()),email)
    }

    val action = AnswerTableObj.map(m => (m.questionId,m.email,m.optionA,m.optionB,m.optionC,m.optionD,m.result, m.createdOn, m.createdBy)) returning AnswerTableObj.map( _.id)  ++= answerSet
    PostgresSupport.db.run(action)

  }

}
