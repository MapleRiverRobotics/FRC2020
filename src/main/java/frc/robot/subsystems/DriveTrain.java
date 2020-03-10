/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.commands.DriveTrainTeleop;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

  private final WPI_TalonSRX leftMaster;
  private final WPI_VictorSPX rightMaster;
  private final WPI_TalonSRX leftSlave;
  private final WPI_TalonSRX rightSlave;
  private final DifferentialDrive differentialDrive;

  /**
   * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For now
   * we just want the primary one.
   */
  public static final int kPIDLoopIdx = 0;

  /**
   * Set to zero to skip waiting for confirmation, set to nonzero to wait and
   * report to DS if action fails.
   * 
   * @return
   */

  public static final int kTimeoutMs = 30;

  public DriveTrain() {
    leftMaster = new WPI_TalonSRX(RobotMap.driveLeftMaster);
    leftMaster.setInverted(true);
    leftMaster.setSensorPhase(true);
    addChild("LeftMaster", leftMaster);

    rightMaster = new WPI_VictorSPX(RobotMap.driveRightMaster);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    rightMaster.setInverted(true);
    /* Config the sensor used for Primary PID and sensor direction */
    rightMaster.setSensorPhase(true);
    addChild("RightMaster", rightMaster);

    leftSlave = new WPI_TalonSRX(RobotMap.driveLeftSlave);
    leftSlave.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    leftSlave.follow(leftMaster);
    leftSlave.setInverted(true);
    addChild("LeftSlave", leftSlave);

    rightSlave = new WPI_TalonSRX(RobotMap.driveRightSlave);
    rightSlave.setInverted(true);
    rightSlave.follow(rightMaster);
    addChild("RightSlave", rightSlave);

    differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
    differentialDrive.setSafetyEnabled(false);
    differentialDrive.setExpiration(0.1);
    differentialDrive.setMaxOutput(1.0);
    differentialDrive.setDeadband(.02);
    addChild("Differential Drive", differentialDrive);

  }

  // @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveTrainTeleop());
  }

  @Override
  public void periodic() {
    // Put code here to be run every loop

  }

  /**
   * Arcade style driving for the DriveTrain.
   *
   * @param speed    Speed in range [-1,1]
   * @param rotation Rotation in range [-1,1]
   */
  public void arcadeDrive(final double speed, final double rotation) {
    differentialDrive.arcadeDrive(speed, rotation);
  }

  public void tankDrive(final double leftSpeed, final double rightSpeed) {
    SmartDashboard.putNumber("leftSpeed", leftSpeed);
    SmartDashboard.putNumber("rightSpeed", rightSpeed);

    differentialDrive.tankDrive(leftSpeed, rightSpeed, false);
  }

  public void stop() {
    differentialDrive.arcadeDrive(0, 0);
  }

  public void moveDistance() {
    rightMaster.set(ControlMode.Position, 1 * 4096);
  }

  public double getPosition() {
    final double position = rightMaster.getSelectedSensorPosition() / 4096.0 * 18.8;
    SmartDashboard.putNumber("DriveTrain Position", position);
    System.out.print("Right motor sensor position ");
    System.out.println(rightMaster.getSelectedSensorPosition());
    return position;
  }

  public void resetPosition() {
    rightMaster.setSelectedSensorPosition(0);
  }

  public void getWheelSpeed() {
    double rightWheelVelocity = this.rightSlave.getSelectedSensorVelocity() / 4096 * 10 * 60;
    double leftWheelVelocity = this.leftMaster.getSelectedSensorVelocity() / 4096 * 10 * 60;

    SmartDashboard.putNumber("Right Wheel Speed", rightWheelVelocity);
    SmartDashboard.putNumber("Left Wheel Speed", leftWheelVelocity);
  }
}
