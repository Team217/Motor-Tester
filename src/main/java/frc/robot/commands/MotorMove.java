/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import org.team217.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

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
        addRequirements(Robot.kMotorSubsystem);
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
            if (Robot.oi.circleDriver.get()) {
                Robot.kMotorSubsystem.setTalon3(speed1);
            }
            else if (Robot.oi.squareDriver.get()) {
                Robot.kMotorSubsystem.setVictor(speed1 * .7, -speed2 * .7);
            }
            else {
                Robot.kMotorSubsystem.setTalon3(0);
            }
        }
        else if (Robot.oi.touchPadDriver.get()) {
            Robot.kMotorSubsystem.setBoth(speed1, speed2);
        }
        else if (Robot.oi.rightTriggerDriver.get()) {
            Robot.kMotorSubsystem.setFalcon(speed1 * .9);
        }
        else if (Robot.oi.rightBumperDriver.get()) {
            Robot.kMotorSubsystem.setSparkMax(speed1, speed2);
            System.out.println("Speed 1: " + speed1);
            System.out.println("Speed 2: " + speed2);
        }
        else if (Robot.oi.circleDriver.get()) {
            Robot.kMotorSubsystem.setTalon3(.3);
        }
        else if (Robot.oi.squareDriver.get()) {
            Robot.kMotorSubsystem.setTalon3(-.3);
        }
        else if (Robot.oi.xDriver.get()) {
            double vel = SmartDashboard.getNumber("Falcon Target Velocity", falconDefaultVel);
            Robot.kMotorSubsystem.setFalconVel(vel);
        }
        else {
            Robot.kMotorSubsystem.setSparkMax(0, 0);
            Robot.kMotorSubsystem.setVictor(0, 0);
            Robot.kMotorSubsystem.setTalon3(0);
            Robot.kMotorSubsystem.setFalcon(0);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.kMotorSubsystem.setSparkMax(0, 0);
        Robot.kMotorSubsystem.setVictor(0, 0);
        Robot.kMotorSubsystem.setTalon3(0);
        Robot.kMotorSubsystem.setFalcon(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
