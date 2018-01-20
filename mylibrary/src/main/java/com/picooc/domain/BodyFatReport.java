package com.picooc.domain;

import java.io.Serializable;

public class BodyFatReport
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private float addFatWeight;
  private float addMuscleWeight;
  private float goalFatRace;
  private float[] raceArray;
  private int realFatState;
  private float reduceFatWeight;

  public float getAddFatWeight()
  {
    return this.addFatWeight;
  }

  public float getAddMuscleWeight()
  {
    return this.addMuscleWeight;
  }

  public float getGoalFatRace()
  {
    return this.goalFatRace;
  }

  public float[] getRaceArray()
  {
    return this.raceArray;
  }

  public int getRealFatState()
  {
    return this.realFatState;
  }

  public float getReduceFatWeight()
  {
    return this.reduceFatWeight;
  }

  public void setAddFatWeight(float paramFloat)
  {
    this.addFatWeight = paramFloat;
  }

  public void setAddMuscleWeight(float paramFloat)
  {
    this.addMuscleWeight = paramFloat;
  }

  public void setGoalFatRace(float paramFloat)
  {
    this.goalFatRace = paramFloat;
  }

  public void setRaceArray(float[] paramArrayOfFloat)
  {
    this.raceArray = paramArrayOfFloat;
  }

  public void setRealFatState(int paramInt)
  {
    this.realFatState = paramInt;
  }

  public void setReduceFatWeight(float paramFloat)
  {
    this.reduceFatWeight = paramFloat;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BodyFatReport
 * JD-Core Version:    0.6.2
 */
