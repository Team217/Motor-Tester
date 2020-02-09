/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.team217.Num;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.*;

import static frc.robot.Constants.*;

public class TalonShoot extends CommandBase {
    /**
     * Creates a new FalconMove.
     */
    public TalonShoot() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Robot.kMotorSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double speed = Num.deadband(Robot.oi.driver.getY(), 0.08);

        if (Robot.oi.rightTriggerDriver.get()) {
            Robot.kMotorSubsystem.setTalon(speed * .9);
        }
        else if (Robot.oi.leftTriggerDriver.get()) {
            double vel = SmartDashboard.getNumber("Talon Target Velocity", talonDefaultVel);
            Robot.kMotorSubsystem.setTalonVel(vel);
        }
        else {
            Robot.kMotorSubsystem.setTalon(0);
        }

        if (Robot.oi.rightBumperDriver.get()) {
            Robot.kMotorSubsystem.setTalon3Vel(5000);
            SmartDashboard.putNumber("Intake Vel", RobotContainer.talon3.getSelectedSensorVelocity());
            SmartDashboard.putNumber("Intake %", RobotContainer.talon3.get());
        }
        else if (Robot.oi.leftBumperDriver.get()) {
            Robot.kMotorSubsystem.setTalon3(-0.5);
        }
        else {
            Robot.kMotorSubsystem.setTalon3(0);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.kMotorSubsystem.setTalon(0);
        Robot.kMotorSubsystem.setTalon3(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
