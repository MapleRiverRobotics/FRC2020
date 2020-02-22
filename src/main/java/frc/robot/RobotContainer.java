/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutonomousRoutine;
//import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.CascadeHook;
import frc.robot.subsystems.DriveTrain;
//import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IndexingBelts;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.LiftingBelts;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static CascadeHook cascadeHook = new CascadeHook();
  public static Shooter shooter = new Shooter();
  public static Intake intake = new Intake();
  public static IndexingBelts indexingBelts = new IndexingBelts();
  public static LiftingBelts liftingBelts = new LiftingBelts();
  public static DriveTrain drivetrain = new DriveTrain();
  public static Lift lift = new Lift();

  public static OI oi = new OI();

  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ParallelRaceGroup m_autoCommand = new AutonomousRoutine().withTimeout(5.0);
  



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
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
