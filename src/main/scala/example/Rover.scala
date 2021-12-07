package example

object Rover {

  def moveRover(grid: Grid, coord: Coord, directionInput: String, commandInput: String): Either[String, RoverState] = {
    for {
      g <- validateGrid(grid)
      roverCoord <- validatedCoords(g, coord)
      roverDirection <- validateDirection(directionInput)
      command <- validatedCommand(commandInput)
      roverState = RoverState(roverCoord, roverDirection)
    } yield move(roverState, command)
  }

  def move(roverState: RoverState, command: Command): RoverState = ???
  def validateGrid(grid: Grid): Either[String, Grid] = ???
  def validatedCoords(grid: Grid, coord: Coord): Either[String, Coord] = ???
  def validateDirection(direction: String): Either[String, Direction] = ???
  def validatedCommand(command: String): Either[String, Command] = ???
}

case class RoverState(coord: Coord, direction: Direction)
case class Grid(x: Int, y: Int)
case class Coord(x: Int, y: Int)

sealed trait Direction
case object N extends Direction
case object E extends Direction
case object S extends Direction
case object W extends Direction

sealed trait Command
case object Forward extends Command
case object RotateClockwise extends Command
case object RotateAntiClockwise extends Command

