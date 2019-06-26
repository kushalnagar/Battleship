package com.kushal.battleship;

import com.kushal.battleship.bean.Ship;
import com.kushal.battleship.common.GameConstants;
import com.kushal.battleship.exception.InvalidInput;

public class BattleTable {

  private int width;
  private char height;

  private int table[][];
  private int totalPointsOnTable = 0;

  public BattleTable(int width, char height) {
    this.width = width;
    this.height = height;
    checkWidth(width);
    checkHeight(height);
    this.table = new int[height - 64][width];
  }

  private void checkHeight(char height) {
    if (height < GameConstants.minBattleTableHeight || height > GameConstants.maxBattleTableHeight) {
      throw new InvalidInput("Invalid Battle Table height, please check the range");
    }
  }

  private void checkWidth(int width) {
    if (width < GameConstants.minBattleTableWidth || width > GameConstants.maxBattleTableWidth) {
      throw new InvalidInput("Invalid Battle Table width, please check the range");
    }
  }

  public int getWidth() {
    return width;
  }

  public char getHeight() {
    return height;
  }

  public int[][] getTable() {
    return table;
  }
  
  public int reducePoints() {
    return --totalPointsOnTable;
  }

  public int getTotalPointsOnTable() {
    return totalPointsOnTable;
  }

  public void addShipToBattleTable(Ship ship, String location) {
    char coordinateY = location.charAt(0);
    int coordinateX = Integer.parseInt(location.substring(1));
    checkShipSize(coordinateY, coordinateX);
    
    for (int i = coordinateY - 65; i < coordinateY - 65 + ship.getHeight(); i++) {
      for (int j = coordinateX - 1; j < coordinateX - 1 + ship.getWidth(); j++) {
        if (table[i][j] != 0) {
          totalPointsOnTable = totalPointsOnTable - table[i][j];
        }
        table[i][j] = ship.getStrength(ship.getType());
        totalPointsOnTable += table[i][j];
      }
    }
  }
  
  private void checkShipSize(char coordinateY, int coordinateX) {
    if (coordinateX < 1 || coordinateX > this.width) {
      throw new InvalidInput(
          "Not a valid x coorinate: Should be between 1 - " + this.width);
    }
    if (coordinateY < 'A' || coordinateY > this.height) {
      throw new InvalidInput(
          "Not a valid x coorinate: Should be between A - " + this.height);
    }
  }
  
  public void showBattleTable() {
    for (int i = 0; i < this.getTable().length; i++) {
      for (int j = 0; j < this.getTable()[i].length; j++) {
        System.out.print(this.getTable()[i][j] + " ");
      }
      System.out.println("");
    }
  }

}
