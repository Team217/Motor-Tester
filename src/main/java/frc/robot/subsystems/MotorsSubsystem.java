/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

/**
 * Add your docs here.
 */
public class MotorsSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Timer t = new Timer();

  @Override
    public void periodic() {}

    public void setVictor(double speed1, double speed2) {
      RobotContainer.victor1.set(speed1);
      RobotContainer.victor2.set(speed2);
    }


    public void setSparkMax(double speed1, double speed2) {
      if (Math.abs(speed1) < 0.9 || Math.abs(speed2) < 0.9) {
        t.stop();
        t.reset();
      }
      else {
        t.start();
      }

      RobotContainer.sparkMax1.set(speed1);
      RobotContainer.sparkMax2.set(speed2);

      if (t.get() > 1.0) {
        double currentMinVel = SmartDashboard.getNumber("Min velocity", Double.MAX_VALUE);
        SmartDashboard.putNumber("Min velocity", Math.min(currentMinVel, RobotContainer.sparkMax2.getVelocity()));
      }

      SmartDashboard.putNumber("Wheel velocity", RobotContainer.sparkMax2.getVelocity());
    }

    public void setBoth(double speed1, double speed2) {
      RobotContainer.sparkMax1.set(speed1);
      RobotContainer.victor2.set(speed2);
    }
    public void setTalon(double speed1){
      RobotContainer.talon1.set(speed1);                      
    }
  
    
  }

