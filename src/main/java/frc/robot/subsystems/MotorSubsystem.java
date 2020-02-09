/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.RobotContainer.*;

/**
 * Add your docs here.
 */
public class MotorSubsystem extends SubsystemBase {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void periodic() {
    }

    public void setVictor(double speed1, double speed2) {
        victor1.set(speed1);
        victor2.set(speed2);
    }

    public void setSparkMax(double speed1, double speed2) {
        sparkMax1.set(speed1);
        sparkMax2.set(speed2);
    }

    public void setBoth(double speed1, double speed2) {
        sparkMax1.set(speed1);
        victor2.set(speed2);
    }

    public void setTalon(double speed) {
        talon1.set(speed);
    }

    public void setTalonVel(double vel) {
        talon1.set(ControlMode.Velocity, vel);
    }

    public void setTalon3(double speed) {
        talon3.set(speed);
    }

    public void setTalon3Vel(double vel) {
        talon3.set(ControlMode.Velocity, vel);
    }

    public void setFalcon(double speed) {
        falcon1.set(speed);
    }

    public void setFalconVel(double vel) {
        falcon1.set(ControlMode.Velocity, vel);
    }
}
