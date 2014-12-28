package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Board

object Application extends Controller {

  val boardForm = Form(
    tuple(
      "name" -> nonEmptyText,
      "height" -> nonEmptyText,
      "width" -> nonEmptyText,
      "volume" -> nonEmptyText,
      "thickness" -> nonEmptyText,
      "image" -> nonEmptyText
    )
  )

  def index = Action {
    Ok("Hello World")
  }

  def boards = Action {
    Ok(views.html.index(Board.all(), boardForm))
  }

  def newBoard = Action { implicit request =>
    boardForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Board.all(), errors)),
      name => {
        Board.create(name, height, width, volume, thickness, image)
        Redirect(routes.Application.boards)
      }
    )
  }

  def delete(id: Long) = TODO

}
