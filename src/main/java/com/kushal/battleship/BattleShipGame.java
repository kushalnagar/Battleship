package com.kushal.battleship;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kushal.battleship.common.GameUtil;

public class BattleShipGame {

  private static final Logger LOG = LogManager.getLogger(GameUtil.class);
 
  public static String playerPlaying;
  Player player1;
  Player player2;

  public static void main(String[] args) {
    BattleShipGame game = new BattleShipGame();
    if (args.length == 0) {
      LOG.error("Input file missing !!");
      System.exit(1);
    }
    // Load the file
    List<String> input = GameUtil.loadInput(args[0]);
    
    GameUtil.setAllowedSizeForShip(input.get(0));

    game.setupBattleGround(input);
  }

  public void setupBattleGround(List<String> input) {
    
    /**
      5 E
      2
      Q 1 1 A1 B2
      P 2 1 D4 C3
      A1 B2 B2 B3
      A1 B2 B3 A1 D1 E1 D4 D4 D5 D5 
     */
    player1 = new Player("Player-1");
    player2 = new Player("Player-2");

    setupPlayer(player1, input);
    setupPlayer(player2, input);

    assignShipAndMissle(input);

    try {
      play();
    } catch (InterruptedException e) {
      LOG.error("Exception while setting up players");
    }
  }

  private void setupPlayer(Player player, List<String> input) {
    player.createBattleTable(input.get(0));
  }

  public void assignShipAndMissle(List<String> input) {
    // Add Ships to each battleArea
    int totalShips = new Integer(input.get(1));

    if (GameUtil.isNumOfShipsAllowed(input.get(0), totalShips)) {
      for (int i = 0; i < totalShips; i++) {
        String[] battleShipDetail = input.get(i + 2).split(" ");
        player1.placeShipInBattleTable(battleShipDetail[0], new Integer(battleShipDetail[1]),
            new Integer(battleShipDetail[2]), battleShipDetail[3]);
        player2.placeShipInBattleTable(battleShipDetail[0], new Integer(battleShipDetail[1]),
            new Integer(battleShipDetail[2]), battleShipDetail[4]);
      }
      player1.getBattleTable().showBattleTable();
      player2.getBattleTable().showBattleTable();
    }
    player1.addMissile(input.get(input.size() - 2));
    player2.addMissile(input.get(input.size() - 1));
  }

  void play() throws InterruptedException {
    BattleShipGame.playerPlaying = player1.getPlayerName();
    new PlayerThread(player1, player2, player1.getPlayerName()).start();
    Thread.sleep(100);
    new PlayerThread(player2, player1, player2.getPlayerName()).start();
  }
}
