package dojo.scala.app

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future
import scala.io.StdIn

object MainApp extends AkkaConfig {
  def main(array: Array[String]): Unit = {

    val handler: HttpRequest => Future[HttpResponse] = Route.asyncHandler {
      get {
        complete("hello world")
      }
    }

    val server = Http().bindAndHandleAsync(handler, "localhost", 8080)
    println(s"Server online at http://localhost:8080/")
    println("Press RETURN to stop...")
    StdIn.readLine()

    server
      .flatMap(_.unbind)
      .onComplete(_ => actorSystem.terminate())
  }
}
