package user

import slick.jdbc.PostgresProfile.api._
import slick.jdbc.GetResult
import utils.PostgresSupport

object UserDao {

  implicit val getQuizResponseJson = GetResult(r => QuizResponse(r.<<,r.<<,r.<<,r.<<))
  def getAllQuiz(email: String)={
    val query = sql"""select q.id,q.name,q.start_date,q.end_date
                      from quiz.quiz q
                      order by start_date asc""".stripMargin.as[QuizResponse]

    PostgresSupport.db.run(query)
  }

  implicit val getQuizQuestionsResponseJson = GetResult(r => QuizQuestionsResponse(r.<<,r.<<,r.<<,r.<<,r.<<,r.<<))
  def getQuestionsByQuizId(email: String,quizId: Long)={
    val query = sql"""select que.id,que.question,que.option_a,que.option_b,que.option_c,que.option_d
                      from  quiz.quiz_question que
                      where que.activeflag = true and que.quiz_id = ${quizId}""".stripMargin.as[QuizQuestionsResponse]

    PostgresSupport.db.run(query)
  }

}
