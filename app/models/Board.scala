package models

case class Board(id: Long, name: String, height: String, width: String, volume: String, thickness: String, imageId: String)

object Board {

  import anorm._
  import anorm.SqlParser._
  import play.api.db._
  import play.api.Play.current
  //maybe try slick?!?!

  val board = {
    get[Long]("Id") ~
    get[String]("BoardName") ~
    get[String]("Height") ~
    get[String]("Width") ~
    get[String]("Volume") ~
    get[String]("Thickness") ~
    get[String]("Image")map {
      case id ~ name ~ height ~ width ~ volume ~ thickness ~ imageId =>
        Board(id, name, height, width, volume, thickness, imageId)
    }
  }

  def all(): List[Board] = DB.withConnection { implicit c =>
    SQL("select * from boards").as(board *)
  }

  def create(name: String, height: String, width: String,
    volume: String, thickness: String, imageId: String) {
    DB.withConnection { implicit c =>
      SQL("""
        insert into boards (BoardName, Height, Width, Volume, Thickness, Image)
        values
        ({boardname}, {height}, {width}, {volume}, {thickness}, {image})
        """).on(
        'boardname -> name,'height -> height, 'width -> width, 'volume -> volume,
          'thickness -> thickness, 'image -> imageId
      ).executeUpdate()
    }
  }
  

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from boards where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }
}
