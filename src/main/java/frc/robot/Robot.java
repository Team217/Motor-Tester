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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commandgroups.*;
import frc.robot.subsystems.*;

import static frc.robot.Constants.*;
import static frc.robot.RobotContainer.*;

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

    SendableChooser<String> shooterMode = new SendableChooser<String>();

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our   This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();
        oi = new OI();
        Shuffleboard.setRecordingFileNameFormat("${date}-${time}");

        shooterMode.setDefaultOption("Default", "Default");
        shooterMode.addOption("Falcon", "Falcon");
        shooterMode.addOption("Talon", "Talon");

        talon3.setup();
        talon3.config_kF(0, 0.128);
        talon3.config_kP(0, 0.03);
        talon3.setSensorPhase(true);

        talon1.setup();
        talon1.config_IntegralZone(0, 200, 30);
        talon1.setSensorPhase(true);
        talon2.follow(talon1);

        falcon1.setup();
        falcon1.config_IntegralZone(0, 100, 30);
        SmartDashboard.setDefaultNumber("Falcon kF", falconDefaultF);
        SmartDashboard.setDefaultNumber("Falcon kP", falconDefaultP);
        SmartDashboard.setDefaultNumber("Falcon kI", falconDefaultI);
        SmartDashboard.setDefaultNumber("Falcon kD", falconDefaultD);
        SmartDashboard.setDefaultNumber("Falcon Target Velocity", falconDefaultVel);

        SmartDashboard.setDefaultNumber("Talon kF", talonDefaultF);
        SmartDashboard.setDefaultNumber("Talon kP", talonDefaultP);
        SmartDashboard.setDefaultNumber("Talon kI", talonDefaultI);
        SmartDashboard.setDefaultNumber("Talon kD", talonDefaultD);
        SmartDashboard.setDefaultNumber("Talon Target Velocity", talonDefaultVel);

        SmartDashboard.putData("Shooter Mode", shooterMode);
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

        if (falcon1.getSelectedSensorVelocity() < 10000) {
            falcon1.configClosedloopRamp(3, 30);
        }
        else {
            falcon1.configClosedloopRamp(0, 30);
        }
        
        double falconkF = SmartDashboard.getNumber("Falcon kF", falconDefaultF);
        double falconkP = SmartDashboard.getNumber("Falcon kP", falconDefaultP);
        double falconkI = SmartDashboard.getNumber("Falcon kI", falconDefaultI);
        double falconkD = SmartDashboard.getNumber("Falcon kD", falconDefaultD);

        falcon1.config_kF(0, falconkF, 30);
        falcon1.config_kP(0, falconkP, 30);
        falcon1.config_kI(0, falconkI, 30);
        falcon1.config_kD(0, falconkD, 30);


        if (talon1.getSelectedSensorVelocity() < 6000) {
            //talon1.configClosedloopRamp(2, 30);
        }
        else {
            talon1.configClosedloopRamp(0, 30);
        }
        
        double talonkF = SmartDashboard.getNumber("Talon kF", talonDefaultF);
        double talonkP = SmartDashboard.getNumber("Talon kP", talonDefaultP);
        double talonkI = SmartDashboard.getNumber("Talon kI", talonDefaultI);
        double talonkD = SmartDashboard.getNumber("Talon kD", talonDefaultD);

        talon1.config_kF(0, talonkF, 30);
        talon1.config_kP(0, talonkP, 30);
        talon1.config_kI(0, talonkI, 30);
        talon1.config_kD(0, talonkD, 30);
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
        if (shooterMode.getSelected().equals("Falcon")) {
            teleopCommand = new FalconGroup();
        }
        else if (shooterMode.getSelected().equals("Talon")) {
            teleopCommand = new TalonGroup();
        }
        else {
            teleopCommand = new TeleopGroup();
        }

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
        autonomousCommand = new TalonGroup();

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
        SmartDashboard.putNumber("Falcon RPM", falcon1.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Falcon Voltage", falcon1.getMotorOutputVoltage());
        SmartDashboard.putNumber("Falcon %", falcon1.get());
        SmartDashboard.putNumber("Falcon Current", falcon1.getSupplyCurrent());
        SmartDashboard.putNumber("Falcon Temp", (1.8 * falcon1.getTemperature() + 32.0));

        SmartDashboard.putNumber("Talon RPM", talon1.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Talon Voltage", talon1.getMotorOutputVoltage());
        SmartDashboard.putNumber("Talon %", talon1.get());
        SmartDashboard.putNumber("Talon Current", talon1.getSupplyCurrent());

        SmartDashboard.putNumber("Battery Voltage", PowerJNI.getVinVoltage());
    }
}
