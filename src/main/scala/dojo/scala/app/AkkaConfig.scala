package dojo.scala.app

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

trait AkkaConfig {
  implicit val actorSystem = ActorSystem()
  implicit val executionContext = actorSystem.dispatcher
  implicit val materializer = ActorMaterializer()
}
