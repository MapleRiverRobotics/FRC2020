/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AimAndShoot extends CommandBase {
  /**
   * Creates a new AimAndShoot.
   */
  double KpAim = -0.1f;
  double KpDistance = 0.1f;
  double min_aim_command = 0.05f;

  double tx;
  double ty;
  double tv;

  public AimAndShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("ledMode").setNumber(3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx").getDouble(0);
    ty = table.getEntry("ty").getDouble(0);
    tv = table.getEntry("tv").getDouble(0);

    SmartDashboard.putNumber("tx", tx);
    SmartDashboard.putNumber("ty", ty);
    SmartDashboard.putNumber("tv", tv);

    double turnSpeed = -0.1 * tx;

    if (turnSpeed >= 0 && turnSpeed <= 0.6) {
      turnSpeed = 0.6;
    } else if (turnSpeed > -.6 && turnSpeed < 0) {
      turnSpeed = -.6;
    }

    // if (tv > 0) {
    if (tx > 1) {
      RobotContainer.drivetrain.arcadeDrive(0, turnSpeed);
      return;
    } else if (tx < -1) {
      RobotContainer.drivetrain.arcadeDrive(0, turnSpeed);
      return;
    }

    // double distance_adjust = KpDistance * distance_error;

    RobotContainer.drivetrain.arcadeDrive(0, 0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.arcadeDrive(0, 0);
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("ledMode").setNumber(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return (tx > -1 && tx < 1);
    return false;
  }
}
