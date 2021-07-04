package admin

import java.sql.Timestamp
import java.time.Instant

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import utils.PostgresSupport


class QuestionTable(tag: Tag) extends Table[Question](tag, Some("quiz"), "quiz_question") {

  def * : ProvenShape[Question] = (id, quizId, question, optionA, optionAFlag, optionB, optionBFlag, optionC, optionCFlag, optionD, optionDFlag, activeFlag, createdOn, createdBy,modifiedOn,modifiedBy) <> (Question.tupled, Question.unapply)

  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def quizId: Rep[Long] = column[Long]("quiz_id")
  def question: Rep[String] = column[String]("question")
  def optionA: Rep[String] = column[String]("option_a")
  def optionAFlag: Rep[Boolean] = column[Boolean]("option_a_flag")
  def optionB: Rep[String] = column[String]("option_b")
  def optionBFlag: Rep[Boolean] = column[Boolean]("option_b_flag")
  def optionC: Rep[String] = column[String]("option_c")
  def optionCFlag: Rep[Boolean] = column[Boolean]("option_c_flag")
  def optionD: Rep[String] = column[String]("option_d")
  def optionDFlag: Rep[Boolean] = column[Boolean]("option_d_flag")
  def activeFlag: Rep[Boolean] = column[Boolean]("activeflag")
  def createdOn: Rep[Timestamp] = column[Timestamp]("createdon")
  def createdBy: Rep[String] = column[String]("createdby")
  def modifiedOn: Rep[Timestamp] = column[Timestamp]("modifiedon")
  def modifiedBy: Rep[String] = column[String]("modifiedby")
}


object QuestionDAO {

  lazy val QuestionTableObj = TableQuery[QuestionTable]

  /*def insertQuestion(email: String,quizId: Long,question: String,optionA: String,optionAFlag: Boolean,optionB: String,optionBFlag: Boolean,optionC: String,optionCFlag: Boolean,optionD: String,optionDFlag: Boolean) = {
    val action = licenseTableObj.map(m => (m.quizId,m.question,m.optionA,m.optionAFlag,m.optionB,m.optionBFlag,m.optionC,m.optionCFlag,m.optionD,m.optionDFlag, m.activeFlag, m.createdOn, m.createdBy)) returning licenseTableObj.map( _.id) += (quizId,question,optionA,optionAFlag,optionB,optionBFlag,optionC,optionCFlag,optionD,optionDFlag,true,Timestamp.from(Instant.now()),email)
    PostgresSupport.db.run(action)
  }*/

  def insertQuestion(questions: Array[QuestionRequest]) = {

    val questionSet = questions.map{que =>
      (que.quizId,que.question,que.optionA,que.optionAFlag,que.optionB,que.optionBFlag,que.optionC,que.optionCFlag,que.optionD,que.optionDFlag,true,Timestamp.from(Instant.now()),que.cratedBy)
    }

    val action = QuestionTableObj.map(m => (m.quizId,m.question,m.optionA,m.optionAFlag,m.optionB,m.optionBFlag,m.optionC,m.optionCFlag,m.optionD,m.optionDFlag, m.activeFlag, m.createdOn, m.createdBy)) returning QuestionTableObj.map( _.id)  ++= questionSet
    PostgresSupport.db.run(action)
  }


  def getQuestionByQuizIs(quizId: Long) = {

    val action = QuestionTableObj.filter(m => m.quizId === quizId && m.activeFlag === true).map(m => (m.quizId,m.id,m.optionA,m.optionAFlag,m.optionB,m.optionBFlag,m.optionC,m.optionCFlag,m.optionD,m.optionDFlag)).result
    PostgresSupport.db.run(action)
  }




}
