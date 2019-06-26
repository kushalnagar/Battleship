package com.kushal.battleship;

public class PlayerThread extends Thread {
  Player playerA;
  Player playerB;

  PlayerThread(Player playerA, Player playerB, String playerPlaying) {
    super(playerPlaying);
    this.playerA = playerA;
    this.playerB = playerB;
  }

  /**
   * This will take care of processing missiles for each player one by one and
   * toggle the chance in case of miss or no missile
   */
  @Override
  public void run() {
    while (!playerA.won && !playerB.won) {
      synchronized (this) {
        if (this.getName().equals(BattleShipGame.playerPlaying)) {
          if (playerA.getMissiles().size() == 0 && playerB.getMissiles().size() == 0) {
            System.out.println(
                playerA.getPlayerName() + " and " + playerA.getPlayerName() + " have no more missiles left to launch");
            System.out.println("Game Draw");
            System.exit(1);
          }

          if (playerA.noMissileLeft()) {
            System.out.println(playerA.getPlayerName() + " has no more missiles left to launch");
            togglePlayer();
          } else {
            boolean hit = true;
            while (hit && playerA.getMissiles().size() > 0) {
              hit = playerA.fire(playerB);
              if (playerB.noShipLeft()) {
                System.out.println(playerA.getPlayerName() + " won the battle");
                playerA.won = true;
                break;
              }
            }
            togglePlayer();
          }
        }
      }
    }
  }

  private synchronized void togglePlayer() {
    BattleShipGame.playerPlaying = playerB.getPlayerName();
  }
}
