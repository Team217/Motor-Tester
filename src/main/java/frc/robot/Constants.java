/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Controllers
    public static final int driverPort = 0;
    public static final int operPort = 1;

    //Motor IDs
    public static final int Victor1_ID = 1;
    public static final int Victor2_ID = 2;
    public static final int Sparkmax1_ID = 3;
    public static final int Sparkmax2_ID = 4;
    public static final int Talon1_ID = 5;
    public static final int Talon2_ID = 8;
    public static final int Talon3_ID = 9;
    public static final int Falcon1_ID = 6;
    public static final int Falcon2_ID = 7;

    // Buttons
    public static final int buttonSquare = 1;
    public static final int buttonX = 2;
    public static final int buttonCircle = 3;
    public static final int buttonTriangle = 4;
    public static final int leftBumper = 5;
    public static final int rightBumper = 6;
    public static final int leftTrigger = 7;
    public static final int rightTrigger = 8;
    public static final int buttonOption = 10;
    public static final int leftAnalog = 11;
    public static final int rightAnalog = 12;
    public static final int touchPad = 14;

    // Axes
    public static final int X_Axis = 0;
    public static final int Y_Axis = 1;
    public static final int Z_Axis = 2;
    public static final int Z_RotateAxis = 5;

    public static double falconDefaultF = 0.0501, falconDefaultP = 2.5, falconDefaultI = 0, falconDefaultD = 0;
    public static double falconDefaultVel = 15000;
    
    public static double talonDefaultF = 0.015, talonDefaultP = 1, talonDefaultI = 0, talonDefaultD = 0;
    public static double talonDefaultVel = 50000;
}
