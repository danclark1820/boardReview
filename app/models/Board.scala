package models

case class Board(id: Long, name: String, height: String, width: String, volume: String, thickness: String, imageId: String)

object Board {

  def all(): List[Board] = Nil

  def create(name: String, height: String, width: String,
    volume: String, thickness: String, imageId: String) {}

  def delete(id: Long) {}
}
