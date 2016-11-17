package dojo.scala.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.io.StdIn

object MainApp {
  def main(array: Array[String]): Unit = {
    implicit val actorSystem = ActorSystem()
    implicit val executionContext = actorSystem.dispatcher
    implicit val materializer = ActorMaterializer()

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
