package com.kushal.battleship;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kushal.battleship.BattleTable;
import com.kushal.battleship.bean.Ship;
import com.kushal.battleship.common.GameUtil;
import com.kushal.battleship.exception.InvalidInput;

public class BattleTableTest {

  @Test(expected = InvalidInput.class)
  public void minWidthTest() {
    int width = 0;
    new BattleTable(width, 'Z');
  }

  @Test(expected = InvalidInput.class)
  public void maxWidthTest() {
    int width = 10;
    new BattleTable(width, 'Z');
  }

  @Test(expected = InvalidInput.class)
  public void minHeightTest() {
    char height = 'z';
    new BattleTable(5, height);
  }

  @Test(expected = InvalidInput.class)
  public void maxHeightTest() {
    char height = '>';
    new BattleTable(5, height);
  }

  @Test
  public void Should_SetBattleAreaWidthAndHeight_When_CreatingBattleArea() {
    BattleTable battleTable = new BattleTable(5, 'Z');
    int size[][] = battleTable.getTable();
    int rows = size.length;
    int cols = size[0].length;
    assertArrayEquals(new int[] { 26, 5 }, new int[] { rows, cols });
  }

  @Test
  public void Should_SetShipLocationAndPower_When_AddShip() {
    BattleTable table = new BattleTable(5, 'E');
    GameUtil.setAllowedSizeForShip("5 E");
    Ship ship = new Ship("Q", 1, 1);
    table.addShipToBattleTable(ship, "B2");

    for (int i = 0; i < table.getTable().length; i++) {
      for (int j = 0; j < table.getTable()[i].length; j++) {
        if (i == 1 && j == 1) {
          assertEquals(2, table.getTable()[i][j]);
        } else {
          assertEquals(0, table.getTable()[i][j]);
        }
      }
    }
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_ShipXCoordinateLessThanBattleAreaWidth() {
    BattleTable battleArea = new BattleTable(5, 'E');
    Ship ship = new Ship("Q", 1, 1);
    battleArea.addShipToBattleTable(ship, "@1");
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_ShipXCoordinateGreaterThanBattleAreaWidth() {
    BattleTable battleArea = new BattleTable(5, 'E');
    Ship ship = new Ship("Q", 1, 1);
    battleArea.addShipToBattleTable(ship, "[1");
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_ShipYCoordinateLessThanBattleAreaHeight() {
    BattleTable battleArea = new BattleTable(5, 'E');
    Ship ship = new Ship("Q", 1, 1);
    battleArea.addShipToBattleTable(ship, "A0");
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_ShipYCoordinateGreaterThanBattleAreaHeight() {
    BattleTable battleArea = new BattleTable(5, 'E');
    Ship ship = new Ship("Q", 1, 1);
    battleArea.addShipToBattleTable(ship, "A10");
  }

  @Test
  public void Should_SetWidth_When_DrawArea() {
    int width = 5;
    BattleTable battleArea = new BattleTable(width, 'E');
    assertEquals(width, battleArea.getWidth());
  }

  @Test
  public void Should_SetHeight_When_DrawArea() {
    char height = 'E';
    BattleTable battleArea = new BattleTable(5, height);
    assertEquals(height, battleArea.getHeight());
  }

}
