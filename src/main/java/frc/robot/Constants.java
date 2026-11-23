// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
public static class ElevatorConstants {
  public final static int LeftLiftmotorID = 22;
  public final static int RightLiftmotorID = 20;  
  public final static double elevatorKp = 0;
  public final static double elevatorKi = 0;
  public final static double elevatorKd = 0;
  public final static double elevatorKs = 0;
  public final static double elevatorKg = 0;
  public final static double elevatorKV = 0;
  public final static double elevatorKa = 0; 
  public final static double elevatorMaxVelocity = 0;
  public final static double elevatorMaxAcceleration = 0; 
  public final static double elevatorRampRate = 0; 
  }
}
