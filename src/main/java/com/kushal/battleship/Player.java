package com.kushal.battleship;

import java.util.LinkedList;
import java.util.Queue;

import com.kushal.battleship.bean.Missile;
import com.kushal.battleship.bean.Ship;

public class Player {

  private String playerName;
  
  Queue<Missile> missiles = new LinkedList<Missile>();
  
  BattleTable battleTable;
  
  boolean out = false;
  
  public boolean won = false;
  
  Player(String name) {
    this.playerName = name;
  }

  public BattleTable createBattleTable(String battleTableValue) {
    String[] values = battleTableValue.split(" ");
    battleTable = new BattleTable(new Integer(values[0]), values[1].charAt(0));
    return battleTable;
  }

  public void placeShipInBattleTable(String type, int width, int height, String location) {
    Ship ship = new Ship(type, width, height);
    battleTable.addShipToBattleTable(ship, location);
  }

  public void addMissile(String input) {
    for (String target : input.split(" ")) {
      Missile m = new Missile(target);
      missiles.add(m);
    }
  }
  public Queue<Missile> getMissiles() {
    return missiles;
  }

  
  public boolean noShipLeft() {
    return (battleTable.getTotalPointsOnTable() == 0) ? true : false;
  }

  /**
   * Fire missile on opposite player
   * @param player
   * @return
   */
  public synchronized boolean fire(Player player) {
    boolean hit = false;
    Missile missile = missiles.remove();
    int value = player.getBattleTable().getTable()[missile.getRow()][missile.getColumn()];
    if (value == 0) {
      System.out.println(playerName + " fires a missile with target " + missile.getTarget() + " which got miss");
    } else {
      System.out.println(playerName + " fires a missile with target " + missile.getTarget() + " which got hit");
      player.getBattleTable().getTable()[missile.getRow()][missile.getColumn()] =
          player.getBattleTable().getTable()[missile.getRow()][missile.getColumn()] - 1;
      player.getBattleTable().reducePoints();
      hit = true;
    }
    return hit;
  }

  
  public String getPlayerName() {
    return playerName;
  }

  public BattleTable getBattleTable() {
    return battleTable;
  }

  public boolean out() {
    return out;
  }

  public boolean noMissileLeft() {
    if(this.missiles.size() == 0) {
      this.out = true;
      return true;
    }
    return false;
  }
}
