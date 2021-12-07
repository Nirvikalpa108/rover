# Mars Rover 

## Introduction
This is a solution to Part 1 of the Mars Rover challenge as described below.

There is no way to run this programme at present. The tests however are working and this is the only way to interact with the programme at present.

## Installation
A JDK, Scala 2 and sbt are required to run this programme. For installation steps, here is some good documentation:
- Scala 2 (https://docs.scala-lang.org/getting-started/index.html?_ga=2.153824446.704741803.1638872802-1052766958.1638872720)
- sbt (https://www.scala-sbt.org/1.x/docs/Setup.html)

## Run
In order to run this programme, you can run the tests by running `sbt` from the root directory of the programme and once inside the sbt interactive shell, type `test`.

## Challenge
The next Mars Rover is being developed, and we need you to come up with a simple way of issuing navigation instructions to it from back on Earth!
We would like you to model the following.
### Part 1: Basic Movement
1. The Mars Rover operates on a grid of arbitrary size.
2. You can only issue three commands: Move forward, rotate clockwise, and rotate anticlockwise.
3. If the rover moves off the grid, it reappears on the opposite side of the grid. 
### Part 2: Autopilot
1. Devise a simple process for determining the shortest possible path from one position on the grid to another.
2. Improve the solution so that it can avoid mountain ranges that occupy a number of inconvenient grid squares scattered around the map.
### Part 3: Putting it all together 
Output all the instructions and moves carried out by the rover to get from one grid square to another. The output can take any form e.g rows of text, JSON data, or something graphical.