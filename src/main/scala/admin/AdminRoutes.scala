package admin

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._
import io.circe.syntax._
import utils.{ActorSupport, CirceJsonSupport, HttpMessageResponse}

object AdminRoutes extends ActorSupport with CirceJsonSupport {


  val route ={
    pathPrefix("admin"){
      pathPrefix("quiz"){
        pathEnd{
          post{
            entity(as[QuizRequest]){obj =>
              complete(QuizDAO.insertQuiz(obj.cratedBy,obj.name,obj.startDate,obj.endDate).map{returnVal =>
                HttpResponse(StatusCodes.OK, entity = new HttpMessageResponse("Success","Quiz Generated Successfully").asJson.toString)
              })
            }
          }
        }~
        path("questions"){
          post{
            entity(as[Array[QuestionRequest]]){obj =>

              complete(QuestionDAO.insertQuestion(obj).map{returnVal =>
                HttpResponse(StatusCodes.OK, entity = new HttpMessageResponse("Success","Questions Added Successfully").asJson.toString)
              })


            }
          }
        }
      }
    }
  }

}
