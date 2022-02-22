package example

object Rover {
  def rove(gridInput: Grid, coordInput: Coord, directionInput: String, commandInput: String): Either[String, RoverState] = {
    for {
      grid <- validateGrid(gridInput)
      coord <- validateCoords(grid, coordInput)
      direction <- validateDirection(directionInput)
      command <- validateCommand(commandInput)
      state = RoverState(coord, direction)
    } yield move(command, state, grid)
  }

  private[example] def move(command: Command, r: RoverState, grid: Grid): RoverState = command match {
    case Forward => RoverState(moveForward(r, grid), r.direction)
    case RotateClockwise => RoverState(r.coord, rotateClockwise(r.direction))
    case RotateAntiClockwise => RoverState(r.coord, rotateAntiClockwise(r.direction))
  }

  /**
   * Given the rover's direction and grid boundaries, determine it's new coordinates after moving forward.
   */
  private def moveForward(rover: RoverState, grid: Grid): Coord = {
    val coord = rover.coord
    val bottomGrid = 1
    rover.direction match {
      case North => if (coord.y == grid.height) Coord(coord.x, bottomGrid) else Coord(coord.x, coord.y + 1)
      case East => if (coord.x == grid.width) Coord(1, coord.y) else Coord(coord.x + 1, coord.y)
      case South => if (coord.y == 1) Coord(coord.x, grid.height) else Coord(coord.x, coord.y - 1)
      case West => if (coord.x == 1) Coord(grid.width, coord.y) else Coord(coord.x - 1, coord.y)
    }
  }
  private def rotateClockwise(d: Direction): Direction = d match {
    case North => East
    case East => South
    case South => West
    case West => North
  }
  private def rotateAntiClockwise(d: Direction): Direction = d match {
    case North => West
    case East => North
    case South => East
    case West => South
  }
  private[example] def validateGrid(grid: Grid): Either[String, Grid] = if (grid.width <= 0 || grid.height <= 0) Left(invalidGrid) else Right(grid)
  private[example] def validateCoords(grid: Grid, coord: Coord): Either[String, Coord] = if (grid.width >= coord.x && grid.height >= coord.y) Right(coord) else Left(invalidCoords)
  private[example] def validateDirection(direction: String): Either[String, Direction] = direction match {
    case "N" => Right(North)
    case "E" => Right(East)
    case "S" => Right(South)
    case "W" => Right(West)
    case _ => Left(invalidDirection)
  }
  private[example] def validateCommand(command: String): Either[String, Command] = command match {
    case "F" => Right(Forward)
    case "RC" => Right(RotateClockwise)
    case "RAC" => Right(RotateAntiClockwise)
    case _ => Left(invalidCommand)
  }

  val invalidGrid = "invalid grid"
  val invalidCoords = "invalid coordinates"
  val invalidDirection = "invalid direction"
  val invalidCommand = "invalid command"
}


sealed trait Error {
  def msg: String
}
case object InvalidGrid extends Error {
  override def msg: String = "invalid grid"
}

case class RoverState(coord: Coord, direction: Direction)
case class Grid(width: Int, height: Int)
case class Coord(x: Int, y: Int)

sealed trait Direction
case object North extends Direction
case object East extends Direction
case object South extends Direction
case object West extends Direction

sealed trait Command
case object Forward extends Command
case object RotateClockwise extends Command
case object RotateAntiClockwise extends Command


