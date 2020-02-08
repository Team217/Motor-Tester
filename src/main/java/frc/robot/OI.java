/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;

public class OI {
    // Joysticks
    public Joystick driver = new Joystick(driverPort);
    public Joystick oper = new Joystick(operPort);

    // Buttons
    public Button squareOper = new JoystickButton(oper, buttonSquare);
    public Button circleOper = new JoystickButton(oper, buttonCircle);
    public Button triangleOper = new JoystickButton(oper, buttonTriangle);
    public Button xOper = new JoystickButton(oper, buttonX);
    public Button leftBumperOper = new JoystickButton(oper, leftBumper);
    public Button rightBumperOper = new JoystickButton(oper, rightBumper);
    public Button leftTriggerOper = new JoystickButton(oper, leftTrigger);
    public Button rightTriggerOper = new JoystickButton(oper, rightTrigger);
    public Button leftAnalogOper = new JoystickButton(oper, leftAnalog);

    // Driver Buttons
    public Button leftBumperDriver = new JoystickButton(driver, leftBumper);
    public Button circleDriver = new JoystickButton(driver, buttonCircle);
    public Button triangleDriver = new JoystickButton(driver, buttonTriangle);
    public Button xDriver = new JoystickButton(driver, buttonX);
    public Button squareDriver = new JoystickButton(driver, buttonSquare);
    public Button rightBumperDriver = new JoystickButton(driver, rightBumper);
    public Button rightTriggerDriver = new JoystickButton(driver, rightTrigger);
    public Button leftAnalogDriver = new JoystickButton(driver, leftAnalog);
    public Button leftTriggerDriver = new JoystickButton(driver, leftTrigger);
    public Button rightAnalogDriver = new JoystickButton(driver, rightAnalog);
    public Button touchPadDriver = new JoystickButton(driver, touchPad);
    
}
