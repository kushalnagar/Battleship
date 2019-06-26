package com.kushal.battleship.bean;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.kushal.battleship.bean.Ship;
import com.kushal.battleship.bean.Ship.ShipType;
import com.kushal.battleship.common.GameUtil;
import com.kushal.battleship.exception.InvalidInput;

public class ShipTest {
  
  @Before
  public void setup(){
    GameUtil.setAllowedSizeForShip("5 E");
  }

  @Test
  public void createShipTest() {
    int width = 3;
    Ship ship = new Ship("P", width, 4);
    assertEquals(width, ship.getWidth());
  }

  @Test
  public void Should_SetHeight_When_CreateShip() {
    int height = 4;
    Ship ship = new Ship("P", 3, height);
    assertEquals(height, ship.getHeight());
  }

  @Test
  public void Should_SetShipType_When_CreateShip() {
    String type = "P";
    Ship ship = new Ship(type, 3, 4);
    assertEquals(type, ship.getType());
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_WidthLessThanDefinedRange() {
    int width = 0;
    new Ship("P", width, 4);
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_WidthGreaterThanDefinedRange() {
    int width = 10;
    new Ship("P", width, 4);
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_ShipTypeOtherThanDefineType() {
    String type = "Z";
    new Ship(type, 9, 4);
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_HeightLessThanDefinedRange() {
    int height = 0;
    new Ship("P", 4, height);
  }

  @Test(expected = InvalidInput.class)
  public void Should_ThrowException_When_HeightGreaterThanDefinedRange() {
    int height = 27;
    new Ship("P", 4, height);
  }

  @Test
  public void Should_ReturnPower_When_GetPower() {
    Ship ship = new Ship("P", 1, 2 );
    assertArrayEquals(new int[] { 1, 2 },
        new int[] { ship.getStrength(ShipType.P.name()), ship.getStrength(ShipType.Q.name()) });
  }

  @Test
  public void Should_ReturnValidShipTypes_When_Instantiated() {
    List<String> actualShipTypes = Stream.of(ShipType.values()).map(Enum::name).collect(Collectors.toList());
    assertThat(actualShipTypes, containsInAnyOrder("P", "Q"));
  }

  @Test
  public void Should_ReturnValidShipPower_When_Instantiated() {
    assertThat(ShipType.valueOf("P").getStrength(), is(1));
    assertThat(ShipType.valueOf("Q").getStrength(), is(2));
  }

}
