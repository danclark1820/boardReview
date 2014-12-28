package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Board

object Application extends Controller {

  case class BoardData(name: String, height: String, width: String, volume: String, thickness: String, image: String)

  val boardForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "height" -> nonEmptyText,
      "width" -> nonEmptyText,
      "volume" -> nonEmptyText,
      "thickness" -> nonEmptyText,
      "image" -> nonEmptyText
    )(BoardData.apply)(BoardData.unapply)
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
      boardForm => {
        Board.create(boardForm.name, boardForm.height, boardForm.width,
          boardForm.volume, boardForm.thickness, boardForm.image)
        Redirect(routes.Application.boards)
      }
    )
  }

  def delete(id: Long) = TODO

}
