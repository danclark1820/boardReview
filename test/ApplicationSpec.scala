import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import play.api.data.Form

import models.Board
import controllers.Application.BoardData
import controllers.Application.boardForm

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get
      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/plain")
      contentAsString(home) must contain ("Hello")
    }
    /***
       This is where you left off. Trying to test rendering a list of boards, 
       you were testing the template as a function.
       Struggling with inputing the boardForm
      */
    "render a list of boards" in {
      val brd = new Board(123456,"al merrick", "5'11''", "20''", "29.3", "2 1/2", "img_123.png")
      val boardList = List(brd, brd, brd)
      val boardIndex = views.html.index(boardList, boardForm)
      contentAsString(boardIndex) must contain("al merrick")
      contentAsString(boardIndex) must contain("3 board(s)")
    }

    /***TODO
    "delete a board from a list" in{
    }
     */

    "render the boards page" in new WithApplication{
      val boards = route(FakeRequest(GET, "/boards")).get
      contentType(boards) must beSome.which(_ == "text/html") 
      status(boards) must equalTo(OK)
      contentAsString(boards) must contain ("board(s)")
    }
  }
}
