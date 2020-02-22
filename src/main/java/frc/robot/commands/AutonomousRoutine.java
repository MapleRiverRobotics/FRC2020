/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AutonomousRoutine extends CommandBase {
  /**
   * Creates a new AutonomousRoutine.
   */
  public AutonomousRoutine() {

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.shooter);
    addRequirements(RobotContainer.liftingBelts);
    addRequirements(RobotContainer.indexingBelts);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.shooter.setShooterSpeed(0.8);
    Timer.delay(1);
    RobotContainer.liftingBelts.Enable(-0.8);
    RobotContainer.indexingBelts.Forward();
    Timer.delay(5);
    RobotContainer.shooter.setShooterSpeed(0);
    RobotContainer.liftingBelts.Enable(0);
    RobotContainer.indexingBelts.Stop();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.setShooterSpeed(0);
    RobotContainer.liftingBelts.Enable(0);
    RobotContainer.indexingBelts.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
