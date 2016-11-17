package dojo.scala.app

import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future
import scala.io.StdIn

object Main extends Servable with App {

  val server = start()

  def handler: HttpRequest => Future[HttpResponse] = Route.asyncHandler {
    get {
      complete("hello world")
    }
  }

  println(s"Server online at http://$interface:$port/")
  println("Press RETURN to stop...")
  StdIn.readLine()

  server.stop()
}
