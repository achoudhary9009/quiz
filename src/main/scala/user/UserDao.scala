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

  implicit val getQuizResultResponseJson = GetResult(r => QuizResultResponse(r.<<,r.<<,r.<<,r.<<,r.<<))
  def getQuizResults(email: String)={

    val query = sql"""select q.id,q.name,count(distinct qq.id) as question,count(distinct qa.id) as attended,
                      (count(case when qa.result = true then 1 else null end)*100)/count(qq.id)::float as result
                      from quiz.quiz q
                      join quiz.quiz_question qq on qq.quiz_id = q.id and qq.activeflag = true
                      join quiz.user_question_answer qa on qa.question_id = qq.id and qa.activeflag = true
                      where qa.email = '#${email}'
                      group by q.id,q.name
                      order by q.id asc
                """.stripMargin.as[QuizResultResponse]
    PostgresSupport.db.run(query)
  }

}
