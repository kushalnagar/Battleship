package com.kushal.battleship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.kushal.battleship.BattleTable;
import com.kushal.battleship.common.GameUtil;
import com.kushal.battleship.Player;

public class PlayerTest {

  private BattleTable initBattleAreaData(Player player) {
    BattleTable battleArea = player.createBattleTable("5 E");
    GameUtil.setAllowedSizeForShip("5 E");
    player.placeShipInBattleTable("P", 1, 1 , "B2");
    return battleArea;
  }
  
  @Test
  public void addShipInBattleAreaTest() {
    Player player = new Player("Player");
    BattleTable battleArea = initBattleAreaData(player);
    player.placeShipInBattleTable("Q", 1, 2, "A1");
    int area[][] = battleArea.getTable();
    assertEquals(2 , area[0][0]);
  }
  
  @Test
  public void playerNameTest() {
    Player player = new Player("Player-1");
    assertEquals("Player-1", player.getPlayerName());
  }

  @Test
  public void addMissleTest() {
    Player player = new Player("Player");
    player.addMissile("C1");
    assertEquals("C1", player.getMissiles().peek().getTarget());
  }

  @Test
  public void fireTest() {
    Player player1 = new Player("Player1");
    Player player2 = new Player("Player2");
    player1.addMissile("B2");
    initBattleAreaData(player2);
    assertTrue(player1.fire(player2));
  }
  
  @Test(expected = RuntimeException.class)
  public void fireTest_failure() {
    Player player1 = new Player("Player1");
    Player player2 = new Player("Player2");
    initBattleAreaData(player2);
    assertTrue(player1.fire(player2));
  }

  @Test
  public void fireTest_Miss() {
    Player player1 = new Player("Player1");
    Player player2 = new Player("Player2");
    player1.addMissile("A2");
    initBattleAreaData(player2);
    assertFalse(player1.fire(player2));
  }

  @Test
  public void isDownTest() {
    Player player = new Player("Player");
    player.createBattleTable("5 E");
    assertTrue(player.noShipLeft());
  }

  @Test
  public void isDownTest_False() {
    Player player = new Player("Player");
    player.createBattleTable("5 E");
    GameUtil.setAllowedSizeForShip("5 E");
    player.placeShipInBattleTable("P", 1, 1, "A1");
    assertFalse(player.noShipLeft());
  }

}
