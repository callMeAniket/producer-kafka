package controllers

import javax.inject._
import play.api.libs.json.JsValue
import play.api.mvc._
import Service.KafkaProducerService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, kafkaProducerService: KafkaProducerService) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def sendMessage(topic: String): Action[JsValue] = Action(parse.json) { (request: Request[JsValue]) =>
    val message = request.body.toString()
    kafkaProducerService.sendMessage(topic, message)
    Ok(s"Message sent to topic $topic: $message")
  }
}
