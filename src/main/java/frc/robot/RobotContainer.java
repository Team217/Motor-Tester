/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.team217.ctre.*;
import org.team217.rev.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // Victors
    public static WPI_VictorSPX victor1 = new WPI_VictorSPX(Victor1_ID);
    public static WPI_VictorSPX victor2 = new WPI_VictorSPX(Victor2_ID);
    public static WPI_VictorSPX victor3 = new WPI_VictorSPX(Talon1_ID);

    // Talons
    public static WPI_TalonSRX talon1 = new WPI_TalonSRX(Talon1_ID);
    public static WPI_TalonSRX talon2 = new WPI_TalonSRX(Talon2_ID);
    public static WPI_TalonSRX talon3 = new WPI_TalonSRX(Talon3_ID);

    // SparkMax
    public static CANSparkMax sparkMax1 = new CANSparkMax(Sparkmax1_ID);
    public static CANSparkMax sparkMax2 = new CANSparkMax(Sparkmax2_ID);

    // Falcons
    public static WPI_TalonFX falcon1 = new WPI_TalonFX(Falcon1_ID);
    public static WPI_TalonFX falcon2 = new WPI_TalonFX(Falcon2_ID);

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    /* public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
     }
    */
}
