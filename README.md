# Battleship Salvo Game 
## Welcome to Battleship salvo

A redesigned Battleship Game

### Board Size

BattleSalvo grids, instead of being 10x10, can have height and width dimensions of any value between 6 and 15 (inclusive)! Height and width dimensions do not need to match. For example, 6x10 and 9x6 would both be valid board dimensions for a game. The size of the board for each player, however, must be identical.

### Ship/Boat Sizes

In Battleship, each boat is positioned horizontally in one row or vertically in one column, and covers a specific number of cells on the board.

In traditional Battleship, the boat sizes are:

- Carrier: Size 5
- Battleship: Size 4
- Destroyer: Size 4
- Submarine: Size 3
- Patrol Boat: Size 2

**BattleSalvo is a bit different**:

- Carrier: Size 6
- Battleship: Size 5
- Destroyer: Size 4
- Submarine: Size 3

### Fleet Size

In Battleship, there is one of each boat type. But, in BattleSalvo, there may be several of each boat type.

The total fleet size may not exceed the smaller board dimension, but must have at least one of each boat type. Therefore, for a board of size 8x11, there should be no more than 8 total boats. Fleet size and boat types will be identical between players. 

### Number of Shots

In traditional Battleship, a player launches a missile once per turn. In BattleSalvo, for each turn, each player launches one missile per non-sunk boat remaining in their fleet.  For example, if you currently have 3 remaining ships in your fleet, you would launch 3 missiles. At the same point in time, if your opponent had 5 ships remaining, they would be able to launch 5 missiles.

### Shooting Order

In BattleSalvo, both players select their shots (target locations), and the shots are exchanged simultaneously. Information about hits is then exchanged, surviving ships are updated, and the process is repeated until one (or both) players have no more surviving ships. Importantly, this means some games will end in ties!

More specifically, the steps for the shooting stage of Salvo are laid out below:

1. Both Players shoot their “Salvo’s”
2. Both Players receive the “Incoming” salvo that their opponent fired
3. Both Players update their ships accordingly, and communicate which of the incoming shots hit
4. Repeat

# Visuals
## Setup
![image](https://github.com/bderbs30/BattleSalvo_BS_Redesign/assets/124754518/ffa98cd3-a7e0-4728-bfb7-7d86d8bdd6bc)
## Round View
![image](https://github.com/bderbs30/BattleSalvo_BS_Redesign/assets/124754518/957cc99f-b5dc-4320-b466-af5df9684746)
## End Game
![image](https://github.com/bderbs30/BattleSalvo_BS_Redesign/assets/124754518/a2e272d1-f149-4b48-bacd-0e0ed961fc59)


