package models

case class Board(id: Long, name: String, height: Double, width: Double, volume: Double, thickness: Double, imageId: String)

object Board {

  def all(): List[Board] = Nil

  def create(name: String, height: Double, width: Double,
    volume: Double, thickness: Double, imageId: String) {}

  def delete(id: Long) {}
}
