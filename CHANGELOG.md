Changes made from PA03 to PA04:

- the board is initialized in the setup method instead of the constructor
  of the Player subclasses
  (This made more sense to have it in the setup method because the board initialization is part of the separate setup
  process)
- The controller is passed in a view object instead of the appendable and initializing the view within the controller
  constructor
  (This made more sense because the controller should not be responsible for initializing the view)
- Got rid of the width and height parameters from the player subclasses constructor's
  (This made more sense because the height and width from the setup method should be used instead)
- Fixed a bug in the takeShots method that was causing the game to crash with the server
  (Needed to fix this for the server implementation to work)
- Edited the Driver method to work with the server implementation. Added the runClient method that is used with the
  server. Also added further command line argument differences to run each program
  (Needed to add this for the server implementation to work)
- Edited the original constructor of ComputerPlayer, HumanPlayer, AbstractPlayer, and BattleShipController to get rid of
  the Random object. Instead, created a new constructor that would be used for testing.
  (Needed to change this due to the previous changes and for the ability to properly test the code)

