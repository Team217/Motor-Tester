/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.hal.PowerJNI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commandgroups.*;
import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command autonomousCommand;
    private Command teleopCommand;

    public RobotContainer robotContainer;

    public static OI oi;

    public static MotorSubsystem kMotorSubsystem = new MotorSubsystem();

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();
        oi = new OI();
        Shuffleboard.setRecordingFileNameFormat("${date}-${time}");

        RobotContainer.talon3.setup();
        RobotContainer.talon3.config_kF(0, 0.5);

        RobotContainer.falcon1.setup();
        RobotContainer.falcon1.config_IntegralZone(0, 100, 30);
        SmartDashboard.setDefaultNumber("kF", defaultF);
        SmartDashboard.setDefaultNumber("kP", defaultP);
        SmartDashboard.setDefaultNumber("kI", defaultI);
        SmartDashboard.setDefaultNumber("kD", defaultD);
        SmartDashboard.setDefaultNumber("Target Velocity", defaultVel);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();

        if (RobotContainer.falcon1.getSelectedSensorVelocity() < 10000) {
            RobotContainer.falcon1.configClosedloopRamp(3, 30);
        }
        else {
            RobotContainer.falcon1.configClosedloopRamp(0, 30);
        }
        
        double kF = SmartDashboard.getNumber("kF", defaultF);
        double kP = SmartDashboard.getNumber("kP", defaultP);
        double kI = SmartDashboard.getNumber("kI", defaultI);
        double kD = SmartDashboard.getNumber("kD", defaultD);

        RobotContainer.falcon1.config_kF(0, kF, 30);
        RobotContainer.falcon1.config_kP(0, kP, 30);
        RobotContainer.falcon1.config_kI(0, kI, 30);
        RobotContainer.falcon1.config_kD(0, kD, 30);
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
        Shuffleboard.stopRecording();
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        teleopCommand = new TeleopGroup();

        if (teleopCommand != null) {
            teleopCommand.schedule();
            Shuffleboard.startRecording();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
        shuffleboard();
    }

    @Override
    public void testInit() {
        autonomousCommand = new FalconGroup();

        if (autonomousCommand != null) {
            autonomousCommand.schedule();
            Shuffleboard.startRecording();
        }
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
        CommandScheduler.getInstance().run();
        shuffleboard();
    }

    public void shuffleboard() {
        SmartDashboard.putNumber("Falcon RPM", RobotContainer.falcon1.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Falcon Voltage", RobotContainer.falcon1.getMotorOutputVoltage());
        SmartDashboard.putNumber("Falcon %", RobotContainer.falcon1.get());
        SmartDashboard.putNumber("Falcon Temp", (1.8 * RobotContainer.falcon1.getTemperature() + 32.0));
        SmartDashboard.putNumber("Falcon Current", RobotContainer.falcon1.getSupplyCurrent());
        SmartDashboard.putNumber("Battery Voltage", PowerJNI.getVinVoltage());
    }
}
