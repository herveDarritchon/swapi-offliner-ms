package actors

import actors.SwapiOfflinerActor._
import akka.actor.{Actor, Props}
import play.api.Logger
import play.api.libs.json.{JsResult, Json}
import play.api.libs.ws.{WSClient, WSRequest}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
  * Created by Hervé Darritchon on 23/12/2015.
  *
  */
case class Film(urls: String)

case class Pilot(urls: String)

case class Vehicle(name: String,
                   model: String,
                   manufacturer: String,
                   cost_in_credits: String,
                   length: String,
                   max_atmosphering_speed: String,
                   crew: String,
                   passengers: String,
                   cargo_capacity: String,
                   consumables: String,
                   vehicle_class: String,
                   pilots: List[String],
                   films: List[String],
                   created: String,
                   edited: String,
                   url: String)


object SwapiOfflinerActor {

  def props = Props[SwapiOfflinerActor]

  case class OfflineVehicleMessage(id: String, ws: WSClient)

}

class SwapiOfflinerActor extends Actor {


  def receive = {
    case OfflineVehicleMessage(id: String, ws: WSClient) => {
      // Log some debug info
      val s = sender()
      implicit val context: ExecutionContext = play.api.libs.concurrent.Execution.Implicits.defaultContext

      val request: WSRequest = ws.url("http://swapi.co/api/vehicles/4")

      val complexRequest: WSRequest =
        request.withHeaders("Accept" -> "application/json")
          .withRequestTimeout(10000)

      implicit val vehicleRead = Json.reads[Vehicle]

      val futureResult: Future[JsResult[Vehicle]] = complexRequest.withFollowRedirects(true).get().map {
        response => {
          Logger.debug(Json.prettyPrint(response.json))
          (response.json).validate[Vehicle]
        }
      }

      futureResult onComplete {
        case Success(j: JsResult[Vehicle]) => {
          Logger.debug("Successful WS call with vehicle in return - name : " + j.get.name)
          s ! j.get
        }
        case Success(somethingElse) => {
          Logger.debug("Successful WS call with weird information from the WS call with " + somethingElse)
          s ! "Swapi responds strange stuff !!!"
        }
        case Failure(error) => {
          s ! "Error while retreiving data for vehicle int Swapi with error " + error.getCause
        }
      }
    }
  }

}