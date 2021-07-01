
import admin.AdminRoutes
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.typesafe.config.ConfigFactory
import user.UserRoutes
import utils.ActorSupport

import scala.io.StdIn

object Server extends ActorSupport {
  def main(args: Array[String]): Unit = {


    implicit val config = ConfigFactory.load()
    val host = config.getString("http.ip")
    val port = config.getInt("http.port")

    def routes = pathPrefix("api"){

      UserRoutes.route ~
      AdminRoutes.route
    }

    val bindingFuture = Http().bindAndHandle(routes, host, port)
    println(s"Server online at http://$host:$port/\nPress RETURN to stop...")

    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }
}
