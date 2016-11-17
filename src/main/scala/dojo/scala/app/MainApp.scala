package dojo.scala.app

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future
import scala.io.StdIn

object MainApp extends AkkaConfig with ServerConfig {
  def main(array: Array[String]): Unit = {

    val handler: HttpRequest => Future[HttpResponse] = Route.asyncHandler {
      get {
        complete("hello world")
      }
    }

    val server = Http().bindAndHandleAsync(handler, interface, port)
    println(s"Server online at http://$interface:$port/")
    println("Press RETURN to stop...")
    StdIn.readLine()

    server
      .flatMap(_.unbind)
      .onComplete(_ => actorSystem.terminate())
  }
}
