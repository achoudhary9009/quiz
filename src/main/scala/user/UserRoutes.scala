package user

import admin.QuestionDAO
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import utils._
import io.circe.generic.auto._
import io.circe.syntax._


object UserRoutes extends ActorSupport with CirceJsonSupport {

  val route = {
    pathPrefix("user"){
      pathPrefix("quiz"){
        pathEnd{
          get{
            parameter('email.as[String]){(email) =>
              complete(UserDao.getAllQuiz(email).map{returnVal =>
                HttpResponse(StatusCodes.OK, entity = new AllQuizHttpResponse("Success","Result Generated",returnVal).asJson.toString)
              })
            }
          }
        }~
        path("questions"){
          get{
            parameters('email.as[String],'quizid.as[Long]){(email,quizId) =>
              complete(UserDao.getQuestionsByQuizId(email,quizId).map{returnVal =>
                HttpResponse(StatusCodes.OK, entity = new QuizQuestionsHttpResponse("Success","Result Generated",returnVal).asJson.toString)
              })
            }
          }
        }~
        path("submit"){
          post{
            parameters('email.as[String],'quizid.as[Long]){(email,quizId)=>
              entity(as[Array[AnswerReceive]]){obj =>
                complete(
                  QuestionDAO.getQuestionByQuizIs(quizId).map{questions =>
                    val result = obj.map{answer =>
                      val question = questions.find(q => q._2 == answer.questionId).get
                      if(question._4 == answer.optionA && question._6 == answer.optionB && question._8 == answer.optionC && question._10 == answer.optionD ){
                        AnswerReceive(answer.questionId,answer.optionA,answer.optionB,answer.optionC,answer.optionD,Some(true))
                      }else{
                        AnswerReceive(answer.questionId,answer.optionA,answer.optionB,answer.optionC,answer.optionD,Some(false))
                      }
                    }
                    AnswerDAO.insertAnswers(result,email)
                  }.flatten.map{m =>
                    HttpResponse(StatusCodes.OK, entity = new HttpMessageResponse("Success","Answer submitted Successfully").asJson.toString)
                  }
                )
              }
            }
          }
        }~
        path("result"){
          get{
            parameters('email.as[String]){(email) =>

              complete(
                UserDao.getQuizResults(email).map{returnVal =>
                  HttpResponse(StatusCodes.OK, entity = new QuizRResultHttpResponse("Success","Result Generated",returnVal).asJson.toString)
                }
              )
            }
          }
        }


      }
    }
  }

}
