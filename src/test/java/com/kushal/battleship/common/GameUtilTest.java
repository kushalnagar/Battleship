package com.kushal.battleship.common;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameUtilTest {
  
  List<String> input;
  
  @Before
  public void setup(){
    input = GameUtil.loadInput("input.txt");
  }

  @Test
  public void loadInputTest(){
    assertEquals(input.size(), 6);
  }
  
  @Test
  public void numberOfShipsInput() {
    // Assert number of ships
    int numberOfShips = new Integer(input.get(1));
    int numberOfDefinition = input.size() - 4;
    assertEquals(numberOfShips, numberOfDefinition);
  }
  
}
