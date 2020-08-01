# Game-Of-Snakes-and-Ladders

To develop this Game for Snakes and Ladders in JAVA, I have created the project in Eclipse IDE.

#### New Features!
  - Customize Snake/Ladder
  - Customize Board Size 
  - No limit on numbers of player
  - Having two type of Dice to play

#### Dice Types
  - Normal Dice - return random from 1,2,3,4,5,6
  - Crooked Dice - retrun random from 2,4,6

#### Rule for adding snakes
  - Snake start position should be greater than end position
  - Snake can not start from equal or more than board size
  - Snake end postion should be greater than 0

#### Rule for adding snakes
  - Ladder start postion should be less than end position
  - Ladder start postion should be greater than 0
  - Ladder end point should not be equal or more than board size
 
> Note: If you create loop using snakes/ladders, I have added check for loop detection also.

#### Usage Guide
I have created driver class having main method for usage [SnakesLaddersGame/src/com/ritvik/game/driver/PlayGame.java][LinkForDriver]

You can also implement the Game interface [SnakesLaddersGame/src/com/ritvik/game/layout/Game.java][LinkForGame]

#### Upcoming Features!
  - I will be creating GUI App using this.
  - I will be creating JAR library from this project for easy integration with custom requirements

   [LinkForDriver]: <https://github.com/s3c-d43m0n/Game-Of-Snakes-and-Ladders/blob/master/SnakesLaddersGame/src/com/ritvik/game/driver/PlayGame.java>
   [LinkForGame]: <https://github.com/s3c-d43m0n/Game-Of-Snakes-and-Ladders/blob/master/SnakesLaddersGame/src/com/ritvik/game/layout/Game.java>
