package controllers

import javax.inject.{Inject, Singleton}

import actors.{Vehicle, SwapiOfflinerActor}
import actors.SwapiOfflinerActor.OfflineVehicleMessage
import akka.actor._
import akka.pattern.ask
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.duration._

@Singleton
class Swapi @Inject()(system: ActorSystem, ws: WSClient) extends Controller {


  val swapiOfflinerActor = system.actorOf(SwapiOfflinerActor.props, "swapi-offliner-actor")


  implicit val timeout = akka.util.Timeout(5.seconds)

  def offlineVehicle(id: String) = Action.async {
    (swapiOfflinerActor ? OfflineVehicleMessage(id, ws)).mapTo[Vehicle].map { message =>
      Logger.debug("Message receive from Actor")
      Ok(message.url)
    }
  }
}