/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.MotorsSubsystem;

import org.team217.Num;

import edu.wpi.first.hal.PDPJNI;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class MotorMove extends CommandBase {
  /**
   * Creates a new ExampleCommand.
   *
   * @param m_MotorSubsystem The subsystem used by this command.
   */
  public MotorMove() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.kMotorsSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double speed1 = Num.deadband(Robot.oi.driver.getY(), 0.08);
    double speed2 = Num.deadband(Robot.oi.driver.getRawAxis(5), 0.08);

    if (Robot.oi.leftBumperDriver.get()) {
      Robot.kMotorsSubsystem.setVictor(speed1, -speed1);
      if (Robot.oi.circleDriver.get()) {
        Robot.kMotorsSubsystem.setTalon(.3);
      } else if (Robot.oi.squareDriver.get()) {
        Robot.kMotorsSubsystem.setTalon(-.3);
      } else {
        Robot.kMotorsSubsystem.setTalon(0);
      }
    } else if (Robot.oi.rightTriggerDriver.get()) {
      Robot.kMotorsSubsystem.setVictor(-1, 1);
    } else if (Robot.oi.rightBumperDriver.get()) {
      Robot.kMotorsSubsystem.setSparkMax(speed1 * .6, speed2 * .6);
      //System.out.println(PDPJNI.getPDPChannelCurrent((byte) 14, PDPJNI.initializePDP(14)));
    }
    else if(Robot.oi.circleDriver.get()){
      Robot.kMotorsSubsystem.setTalon(.3);
  }
    else if(Robot.oi.squareDriver.get()){
      Robot.kMotorsSubsystem.setTalon(-.3);
  } 
  //else if(Robot.oi.rightTriggerDriver.get()){
    //Robot.kMotorsSubsystem.setVictor(-0.3, -0.3);
  //}
    else {
      Robot.kMotorsSubsystem.setSparkMax(0, 0);
      Robot.kMotorsSubsystem.setVictor(0, 0);
      Robot.kMotorsSubsystem.setTalon(0);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
