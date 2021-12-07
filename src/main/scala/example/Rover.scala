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
  private def moveForward(rover: RoverState, g: Grid): Coord = {
    val c = rover.coord
    rover.direction match {
      case North => if (c.y == g.y) Coord(c.x, 1) else Coord(c.x, c.y + 1)
      case East => if (c.x == g.x) Coord(1, c.y) else Coord(c.x + 1, c.y)
      case South => if (c.y == 1) Coord(c.x, g.y) else Coord(c.x, c.y - 1)
      case West => if (c.x == 1) Coord(g.x, c.y) else Coord(c.x - 1, c.y)
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
  private[example] def validateGrid(grid: Grid): Either[String, Grid] = if (grid.x <= 0 || grid.y <= 0) Left(invalidGrid) else Right(grid)
  private[example] def validateCoords(grid: Grid, coord: Coord): Either[String, Coord] = if (grid.x >= coord.x && grid.y >= coord.y) Right(coord) else Left(invalidCoords)
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

case class RoverState(coord: Coord, direction: Direction)
case class Grid(x: Int, y: Int)
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


