package user

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
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
                HttpResponse(StatusCodes.OK, entity = new AllQuizHttpResponse("Sucess","Result Generated",returnVal).asJson.toString)
              })
            }
          }
        }~
        path("questions"){
          get{
            parameter('email.as[String],'quizid.as[Long]){(email,quizId) =>
              complete(UserDao.getQuestionsByQuizId(email,quizId).map{returnVal =>
                HttpResponse(StatusCodes.OK, entity = new QuizQuestionsHttpResponse("Sucess","Result Generated",returnVal).asJson.toString)
              })
            }
          }
        }





      }
    }
  }

}
