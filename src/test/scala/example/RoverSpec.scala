package example

import example.Rover._
import org.scalatest.EitherValues
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class RoverSpec extends AnyFreeSpec with Matchers with EitherValues {
  "validateGrid" - {
    "given a 4x4 grid, return a Right" in {
      validateGrid(Grid(4,4)) should matchPattern { case Right(Grid(4,4)) => }
    }
    "given a 0x0 grid, return a Left" in {
      validateGrid(Grid(0,0)) should matchPattern { case Left(Rover.invalidGrid) => }
    }
    "given a -1x4 grid, return a Left" in {
      validateGrid(Grid(-1,4)) should matchPattern { case Left(Rover.invalidGrid) => }
    }
  }
  "validateCoords" - {
    "given co-ordinates present on a grid, return a Right" in {
      validateCoords(Grid(2, 2), Coord(1, 1)) should matchPattern { case Right(Coord(1,1)) => }
    }
    "given co-ordinates that are not present on a grid, return a Left" in {
      validateCoords(Grid(2, 2), Coord(3, 1)) should matchPattern { case Left(Rover.invalidCoords) => }
    }
  }
  "validateDirection" - {
    "given N, return a Right of North" in {
      validateDirection("N") should matchPattern { case Right(North) => }
    }
    "given E, return a Right of East" in {
      validateDirection("E") should matchPattern { case Right(East) => }
    }
    "given s, return a Left" in {
      validateDirection("s") should matchPattern { case Left(Rover.invalidDirection) => }
    }
    "given Z return a Left" in {
      validateDirection("Z") should matchPattern { case Left(Rover.invalidDirection) => }
    }
  }
  "validateCommand" - {
    "given F, return a Right of Forward" in {
      validateCommand("F") should matchPattern { case Right(Forward) => }
    }
    "given RC, return a Right of Rotate Clockwise" in {
      validateCommand("RC") should matchPattern { case Right(RotateClockwise) => }
    }
    "given RAC, return a Right of Rotate AntiClockwise" in {
      validateCommand("RAC") should matchPattern { case Right(RotateAntiClockwise) => }
    }
    "given f, return a Left" in {
      validateCommand("f") should matchPattern { case Left(Rover.invalidCommand) => }
    }
    "given Z, return a Left" in {
      validateCommand("Z") should matchPattern { case Left(Rover.invalidCommand) => }
    }
  }
  "move" - {
    val grid = Grid(4,4)
    val roverState = RoverState(Coord(1,1), North)
    "given the Forward command, go forward one unit" in {
      move(Forward, roverState, grid) shouldEqual RoverState(Coord(1,2), North)
    }
    "given the RotateClockwise command, turn correctly" in {
      move(RotateClockwise, roverState, grid) shouldEqual RoverState(Coord(1,1), East)
    }
    "given the RotateAntiClockwise command, turn correctly" in {
      move(RotateAntiClockwise, roverState, grid) shouldEqual RoverState(Coord(1,1), West)
    }
    "given a Forward command that leads the rover off the grid at the top, return the correct coordinates" in {
      move(Forward, RoverState(Coord(1,4), North), grid) shouldEqual RoverState(Coord(1,1), North)
    }
    "given a Forward command that leads the rover off the grid at the bottom, return the correct coordinates" in {
      move(Forward, RoverState(Coord(4,2), East), grid) shouldEqual RoverState(Coord(1,2), East)
    }
    "given a Forward command that leads the rover off the grid's bottom left side, return the correct coordinates" in {
      move(Forward, RoverState(Coord(1,1), West), grid) shouldEqual RoverState(Coord(4,1), West)
    }
    "given a Forward command that leads the rover off the grid's top right side, return the correct coordinates" in {
      move(Forward, RoverState(Coord(4,4), East), grid) shouldEqual RoverState(Coord(1,4), East)
    }
  }
}
