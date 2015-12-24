package controllers

import javax.inject.{Inject, Singleton}

import actors.HelloActor
import actors.HelloActor.SayHello
import akka.actor._
import akka.pattern.ask
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._

import scala.concurrent.duration._

@Singleton
class Application @Inject()(system: ActorSystem) extends Controller {


  val helloActor = system.actorOf(HelloActor.props, "hello-actor")


  implicit val timeout = akka.util.Timeout(5.seconds)

  def sayHello(name: String) = Action.async {
    (helloActor ? SayHello(name)).mapTo[String].map { message =>
      Ok(message)

    }
  }

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
