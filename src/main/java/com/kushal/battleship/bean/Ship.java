package com.kushal.battleship.bean;

import com.kushal.battleship.common.GameConstants;
import com.kushal.battleship.exception.InvalidInput;

public class Ship {

  int width;
  int height;
  String type;
  
  public Ship(String type, int width, int height) {
    this.width = width;
    this.height = height;
    this.type = type;
    verifyShip(type, width, height);
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getStrength(String type) {
    return ShipType.valueOf(type).getStrength();
  }
  
  public enum ShipType {

    P(1), Q(2);
    
    private final int strength;

    ShipType(int strength) {
          this.strength = strength;
      }

    public int getStrength() {
      return strength;
    }
    
  }
  
  /**
   * Verifies if the ship size and type is valid
   * @param type
   * @param width
   * @param height
   */
  public void verifyShip(String type, int width, int height) {
    this.width = width;
    this.height = height;
    if (width < GameConstants.minWidth || width > GameConstants.maxWidth) {
      throw new InvalidInput("Invalid Battle width: width[" + width + "], " + " Width must be between(inclusive) "
        + GameConstants.minWidth + " and " + GameConstants.maxWidth);
    }
    if (height < GameConstants.minHeight - 64 || height > GameConstants.maxHeight - 64) {
      throw new InvalidInput("Invalid Battle height: height[" + height + "]. Height must be between(inclusive) "
        + GameConstants.minHeight + " and " + GameConstants.maxHeight);
    }
    this.type = type;
    boolean isShipTypeValid = false;
    for (ShipType shipType : ShipType.values()) {
      if (shipType.name().equals(type)) {
        isShipTypeValid = true;
        break;
      }
    }
    if (!isShipTypeValid) {
      throw new InvalidInput("Invalid Ship Type [" + type + "]. Expected " + ShipType.values());
    }
  }

}
