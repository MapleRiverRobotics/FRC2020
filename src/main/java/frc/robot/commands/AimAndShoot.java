/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import java.lang.Math;

public class AimAndShoot extends CommandBase {
  /**
   * Creates a new AimAndShoot.
   */
  double KpAim = -0.025f;
  // double KpDistance = 0.1f;
  // double min_aim_command = 0.05f;

  double minSpeed = 0.3;
  double minDegreeOffset = 1;

  double tx;
  double ty;
  double tv;

  public AimAndShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.drivetrain);
    addRequirements(RobotContainer.liftingBelts);
    addRequirements(RobotContainer.shooter);
    addRequirements(RobotContainer.indexingBelts);
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

    double turnSpeed = KpAim * tx;

    if (0 < turnSpeed && turnSpeed < minSpeed) {
      turnSpeed = minSpeed;
    } else if (0 > turnSpeed && turnSpeed > -minSpeed) {
      turnSpeed = -minSpeed;
    }

    if (tx > minDegreeOffset) {
      RobotContainer.drivetrain.tankDrive(turnSpeed, 0);
      return;
    } else if (tx < -minDegreeOffset) {
      RobotContainer.drivetrain.tankDrive(turnSpeed, 0);
      return;
    }

    RobotContainer.drivetrain.tankDrive(0, 0);
    if ((tx > -minDegreeOffset && tx < minDegreeOffset) && tx != 0) {
      // GetShooterRpm();
      RobotContainer.shooter.setShooterSpeed(GetShooterRpm());
      if (RobotContainer.shooter.isWheelUpToSpeed() == true) {
        Timer.delay(1);
        RobotContainer.indexingBelts.Forward();
        RobotContainer.liftingBelts.Enable(-0.8);
      }
    }
  }

  private double GetShooterRpm() {
    // d = (h2-h1) / tan(a1+a2)
    double a1 = 7.5; // angle of camera
    double h1 = 24.0; // height of camera
    double h2 = 92.0; // height of target
    double distance = (h2 - h1) / Math.tan(Math.toRadians(a1 + ty));

    SmartDashboard.putNumber("distance", distance);

    return ((distance - 130) * 1.5) + 3500;
//2.1739130434782608695652173913043
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.tankDrive(0, 0);
    RobotContainer.liftingBelts.Stop();
    RobotContainer.shooter.Stop();
    RobotContainer.indexingBelts.Stop();
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("ledMode").setNumber(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return (tx > -minDegreeOffset && tx < minDegreeOffset) && tx != 0;
    return false;
  }
}
