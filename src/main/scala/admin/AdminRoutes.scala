package admin

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import utils.{ActorSupport, CirceJsonSupport, LongHttpResponse}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._
import io.circe.syntax._

object AdminRoutes extends ActorSupport with CirceJsonSupport {


  val route ={
    pathPrefix("admin"){
      pathPrefix("quiz"){
        pathEnd{
          post{
            entity(as[QuizRequest]){obj =>
              complete(QuizDAO.insertQuiz(obj.cratedBy,obj.name,obj.startDate,obj.endDate).map{returnVal =>
                HttpResponse(StatusCodes.OK, entity = new LongHttpResponse("Success","Result Generated",returnVal).asJson.toString)
              })
            }
          }
        }~
        path("questions"){
          post{
            entity(as[String]){obj =>



              complete("")
            }
          }
        }
      }
    }
  }

}
