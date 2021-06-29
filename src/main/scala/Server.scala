
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.io.StdIn

object Server {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("Riverus-riauth-api")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher


    implicit val config = ConfigFactory.load()
    val host = config.getString("http.ip")
    val port = config.getInt("http.port")

    def routes = pathPrefix("api"/"quiz"){

      complete("test.")
    }

    val bindingFuture = Http().bindAndHandle(routes, host, port)
    println(s"Server online at http://$host:$port/\nPress RETURN to stop...")

    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }
}
