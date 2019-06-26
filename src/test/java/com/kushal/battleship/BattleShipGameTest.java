package com.kushal.battleship;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kushal.battleship.BattleShipGame;
import com.kushal.battleship.common.GameUtil;

public class BattleShipGameTest {

  static BattleShipGame game = new BattleShipGame();
  static List<String> input;

  @BeforeClass
  public static void setup() {
    input = GameUtil.loadInput("input.txt");
    GameUtil.setAllowedSizeForShip("5 E");
  }

  @Test
  public void setupBattleGroundTest() {
    game.setupBattleGround(input);
    while (game.player1.noShipLeft())
      assertTrue(game.player1.noShipLeft());
  }
}
