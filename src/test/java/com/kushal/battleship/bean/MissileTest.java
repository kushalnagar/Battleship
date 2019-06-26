package com.kushal.battleship.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.kushal.battleship.bean.Missile;

public class MissileTest {

  @Ignore
  @Test
  public void InvalidTarget_Broken() {
    Missile m = new Missile("a90");
    assertEquals("Z9", m.getTarget());
  }
  
  @Test
  public void getTargetTest() {
    Missile m = new Missile("Z9");
    assertEquals("Z9", m.getTarget());
  }
  
  @Test
  public void getRowTest() {
    Missile m = new Missile("Z9");
    assertEquals(25, m.getRow());
  }

  @Test
  public void getColumnTest() {
    Missile m = new Missile("Z9");
    assertEquals(8, m.getColumn());
  }

}
